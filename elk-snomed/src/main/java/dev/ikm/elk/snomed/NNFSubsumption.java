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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;

public class NNFSubsumption {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(NNFSubsumption.class);

	private SnomedIsa isa;

	private HashMap<RoleType, Set<RoleType>> superRoles;

	protected HashMap<Concept, Definition> necessaryNormalForm;

	public NNFSubsumption(SnomedIsa isa, HashMap<RoleType, Set<RoleType>> superRoles,
			HashMap<Concept, Definition> necessaryNormalForm) {
		super();
		this.isa = isa;
		this.superRoles = superRoles;
		this.necessaryNormalForm = necessaryNormalForm;
	}

	private HashSet<Role> expandChain(RoleType role_type, Concept filler) {
		HashSet<Role> roles = new HashSet<>();
		roles.add(new Role(role_type, filler));
		while (true) {
			ArrayList<Role> roles_l = new ArrayList<>(roles);
			for (Role role : roles_l) {
				List<Role> chain_exps = expandChain1(role);
				roles.addAll(chain_exps);
				List<Role> sup_exps = expandSuperRoleTypes(role);
				roles.addAll(sup_exps);
			}
			if (roles.size() == roles_l.size())
				break;
		}
		return roles;
	}

	private List<Role> expandChain1(Role svf) {
		ArrayList<RoleType> chained_rts = new ArrayList<>();
		if (svf.getRoleType().getChained() != null) {
			chained_rts.add(svf.getRoleType().getChained());
		}
		if (svf.getRoleType().isTransitive())
			chained_rts.add(svf.getRoleType());
		List<Concept> chained_cons = necessaryNormalForm.get(svf.getConcept()).getUngroupedRoles().stream()
				.filter(x -> chained_rts.contains(x.getRoleType())).map(x -> x.getConcept()).distinct().toList();
		return chained_cons.stream().map(x -> new Role(svf.getRoleType(), x)).toList();
	}

	private List<Role> expandSuperRoleTypes(Role role) {
		return superRoles.get(role.getRoleType()).stream().filter(rt -> !rt.equals(role.getRoleType()))
				.map(rt -> new Role(rt, role.getConcept())).toList();
	}

	private boolean isSubsumedBy1(RoleType role1, RoleType role2) {
		return superRoles.get(role1).contains(role2);
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

	protected boolean isSubsumedBy(RoleGroup rg1, RoleGroup rg2) {
		return rg2.getRoles().stream()
				.allMatch(role2 -> rg1.getRoles().stream().anyMatch(role1 -> isSubRoleOfEntailed(role1, role2)));
	}

	protected boolean isSubRoleOfEntailed(Role role1, Role role2) {
		if (isSubsumedBy1(role1.getRoleType(), role2.getRoleType())) {
			{
				Concept con1 = role1.getConcept();
				Concept con2 = role2.getConcept();
				if (isSubsumedBy1(con1, con2))
					return true;
			}
			HashSet<Role> chain1 = expandChain(role1.getRoleType(), role1.getConcept());
			HashSet<Role> chain2 = expandChain(role2.getRoleType(), role2.getConcept());
			boolean isSubsumedBy = chain2.stream().allMatch(
					chain2_role -> chain1.stream().anyMatch(chain1_role -> isSubsumedBy(chain1_role, chain2_role)));
			return isSubsumedBy;
		}
		return false;
	}

}
