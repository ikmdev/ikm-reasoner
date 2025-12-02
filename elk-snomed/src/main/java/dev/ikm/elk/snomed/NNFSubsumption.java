package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2024 Integrated Knowledge Management
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Map;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.model.ConcreteRoleType;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;

public class NNFSubsumption {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(NNFSubsumption.class);

	private SnomedIsa isa;

	// Use Eclipse Collections - better performance
	private MutableMap<RoleType, MutableSet<RoleType>> superRoles;

	protected MutableMap<Concept, Definition> necessaryNormalForm;

	public NNFSubsumption(SnomedIsa isa, Map<RoleType, ? extends MutableSet<RoleType>> superRoles,
			MutableMap<Concept, Definition> necessaryNormalForm) {
		super();
		this.isa = isa;
		this.superRoles = Maps.mutable.ofMap(superRoles);
		this.necessaryNormalForm = necessaryNormalForm;
	}

	private MutableSet<Role> expandChain(RoleType role_type, Concept filler) {
		MutableSet<Role> roles = Sets.mutable.with(new Role(role_type, filler));
		
		while (true) {
			int initialSize = roles.size();
			
			// Use Eclipse Collections to avoid copying to ArrayList
			MutableList<Role> rolesToExpand = Lists.mutable.withAll(roles);
			
			rolesToExpand.forEach(role -> {
				roles.addAll(expandChain1(role));
				roles.addAll(expandSuperRoleTypes(role));
			});
			
			// Break if no new roles were added
			if (roles.size() == initialSize)
				break;
		}
		return roles;
	}

	private MutableList<Role> expandChain1(Role svf) {
		MutableList<RoleType> chained_rts = Lists.mutable.empty();
		
		if (svf.getRoleType().getChained() != null) {
			chained_rts.add(svf.getRoleType().getChained());
		}
		if (svf.getRoleType().isTransitive()) {
			chained_rts.add(svf.getRoleType());
		}
		
		// Use Eclipse Collections select/collect instead of stream
		MutableList<Concept> chained_cons = Lists.mutable.withAll(necessaryNormalForm.get(svf.getConcept())
						.getUngroupedRoles())
				.select(x -> chained_rts.contains(x.getRoleType()))
				.collect(Role::getConcept)
				.distinct();

		return chained_cons.collect(concept -> new Role(svf.getRoleType(), concept));
	}

	private MutableList<Role> expandSuperRoleTypes(Role role) {
		// Use Eclipse Collections select/collect - no stream overhead
		return superRoles.get(role.getRoleType())
				.select(rt -> !rt.equals(role.getRoleType()))
				.collect(rt -> new Role(rt, role.getConcept()))
				.toList();
	}

	private boolean isSubsumedBy1(RoleType role1, RoleType role2) {
		return superRoles.get(role1).contains(role2);
	}

	private boolean isSubsumedBy1(ConcreteRoleType role1, ConcreteRoleType role2) {
		// TODO Handle super types if they are introduced
		return role1.equals(role2);
	}

	private boolean isSubsumedBy1(Concept con1, Concept con2) {
		if (con1.equals(con2))
			return true;
		return isa.hasAncestor(con1.getId(), con2.getId());
	}

	private boolean isSubsumedBy(Role role1, Role role2) {
		return isSubsumedBy1(role1.getRoleType(), role2.getRoleType())
				&& isSubsumedBy1(role1.getConcept(), role2.getConcept());
	}

	protected boolean isSubsumedBy(ConcreteRole role1, ConcreteRole role2) {
		return isSubsumedBy1(role1.getConcreteRoleType(), role2.getConcreteRoleType())
				&& role1.getValueType().equals(role2.getValueType()) 
				&& role1.getValue().equals(role2.getValue());
	}

	protected boolean isSubsumedBy(RoleGroup rg1, RoleGroup rg2) {
		// Use Eclipse Collections allSatisfy/anySatisfy instead of stream
		return rg2.getRoles().allSatisfy(role2 -> 
			rg1.getRoles().anySatisfy(role1 -> 
				isSubRoleOfEntailed(role1, role2)));
	}

	protected boolean isSubRoleOfEntailed(Role role1, Role role2) {
		if (isSubsumedBy1(role1.getRoleType(), role2.getRoleType())) {
			// Quick check before expensive chain expansion
			Concept con1 = role1.getConcept();
			Concept con2 = role2.getConcept();
			if (isSubsumedBy1(con1, con2))
				return true;
			
			// Expand chains only when needed
			MutableSet<Role> chain1 = expandChain(role1.getRoleType(), role1.getConcept());
			MutableSet<Role> chain2 = expandChain(role2.getRoleType(), role2.getConcept());
			
			// Use Eclipse Collections allSatisfy/anySatisfy - more efficient than stream
			return chain2.allSatisfy(chain2_role -> 
				chain1.anySatisfy(chain1_role -> 
					isSubsumedBy(chain1_role, chain2_role)));
		}
		return false;
	}
}
