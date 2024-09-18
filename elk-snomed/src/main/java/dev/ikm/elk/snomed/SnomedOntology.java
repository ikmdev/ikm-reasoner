package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 Integrated Knowledge Management
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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRoleType;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;

public class SnomedOntology {

	private HashMap<Long, Concept> conceptIdMap;

	private List<Concept> concepts;

	private HashMap<Long, RoleType> roleTypeIdMap;

	private List<RoleType> roleTypes;

	private HashMap<Long, ConcreteRoleType> concreteRoleTypeIdMap;

	private List<ConcreteRoleType> concreteRoleTypes;

	private SnomedDescriptions descriptions;

	public Concept getConcept(long id) {
		return conceptIdMap.get(id);
	}

	public RoleType getRoleType(long id) {
		return roleTypeIdMap.get(id);
	}

	public ConcreteRoleType getConcreteRoleType(long id) {
		return concreteRoleTypeIdMap.get(id);
	}

	public List<Concept> getConcepts() {
		return concepts;
	}

	public List<RoleType> getRoleTypes() {
		return roleTypes;
	}

	public List<ConcreteRoleType> getConcreteRoleTypes() {
		return concreteRoleTypes;
	}

	public SnomedDescriptions getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(SnomedDescriptions descriptions) {
		this.descriptions = descriptions;
	}

	public SnomedOntology(Collection<Concept> concepts, Collection<RoleType> roleTypes,
			Collection<ConcreteRoleType> concreteRoleTypes) {
		super();
		this.conceptIdMap = new HashMap<>();
		concepts.forEach(x -> conceptIdMap.put(x.getId(), x));
		this.concepts = new ArrayList<>(conceptIdMap.values());
		this.roleTypeIdMap = new HashMap<>();
		roleTypes.forEach(x -> roleTypeIdMap.put(x.getId(), x));
		this.roleTypes = new ArrayList<>(roleTypes);
		this.concreteRoleTypeIdMap = new HashMap<>();
		concreteRoleTypes.forEach(x -> concreteRoleTypeIdMap.put(x.getId(), x));
		this.concreteRoleTypes = new ArrayList<>(concreteRoleTypes);
	}

	public String getFsn(long concept) {
		if (descriptions != null)
			return descriptions.getFsn(concept);
		return "<" + concept + ">";
	}

	public HashSet<Concept> getDependentOnConcepts(Concept concept) {
		return getDependentOnConcepts(concept, true, true);
	}

	public HashSet<Long> getDependentOnConcepts(long concept) {
		return getDependentOnConcepts(concept, true, true);
	}

	public HashSet<Long> getDependentOnConcepts(long concept, boolean includeSuperConcepts, boolean includeGcis) {
		return getDependentOnConcepts(getConcept(concept), includeSuperConcepts, includeGcis).stream()
				.map(Concept::getId).collect(Collectors.toCollection(HashSet::new));
	}

	public HashSet<Concept> getDependentOnConcepts(Concept concept, boolean includeSuperConcepts, boolean includeGcis) {
		HashSet<Concept> deps = new HashSet<>();
		for (Definition def : concept.getDefinitions()) {
			deps.addAll(getDependentOnConcepts(def, includeSuperConcepts));
		}
		if (includeGcis) {
			for (Definition def : concept.getGciDefinitions()) {
				deps.addAll(getDependentOnConcepts(def, includeSuperConcepts));
			}
		}
		return deps;
	}

	public HashSet<Concept> getDependentOnConcepts(Definition def, boolean includeSuperConcepts) {
		HashSet<Concept> deps = new HashSet<>();
		if (includeSuperConcepts) {
			for (Concept sup : def.getSuperConcepts()) {
				deps.add(sup);
			}
		}
		for (Role role : def.getUngroupedRoles()) {
			deps.add(role.getConcept());
		}
		for (RoleGroup rg : def.getRoleGroups()) {
			for (Role role : rg.getRoles()) {
				deps.add(role.getConcept());
			}
		}
		return deps;
	}

}
