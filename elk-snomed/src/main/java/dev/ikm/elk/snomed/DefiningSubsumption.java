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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.RoleType;

public class DefiningSubsumption extends NNFSubsumption {

	private SnomedOntology ontology;

	private SnomedIsa definingIsa;

	public DefiningSubsumption(SnomedOntology ontology, SnomedIsa definingIsa, SnomedIsa isa,
			HashMap<RoleType, Set<RoleType>> superRoles, HashMap<Concept, Definition> necessaryNormalForm) {
		super(isa, superRoles, necessaryNormalForm);
		this.ontology = ontology;
		this.definingIsa = definingIsa;
	}

	/*
	 * Return true if con1 is subsumed by con2
	 */
	public boolean isSubsumedByStructural(Concept con1, Concept con2) {
		if (con1.equals(con2))
			throw new RuntimeException(con1 + " " + con2);
		Definition def1 = necessaryNormalForm.get(con1);
		Definition def2 = necessaryNormalForm.get(con2);
//		LOG.info("s1:" + def1.getSuperConcepts());
//		LOG.info("s2:" + def2.getSuperConcepts());
		HashSet<Long> ancestors2 = definingIsa.getAncestors(con2.getId());
		ancestors2.add(con2.getId());
		ancestors2.removeIf(anc2 -> anc2 != SnomedIds.root && ontology.getConcept(anc2).getDefinitions().getFirst()
				.getDefinitionType() == DefinitionType.EquivalentConcept);
		if (!ancestors2.stream().allMatch(anc2 -> definingIsa.hasAncestor(con1.getId(), anc2)))
			return false;
//		LOG.info("r1:" + def1.getUngroupedRoles());
//		LOG.info("r2:" + def2.getUngroupedRoles());
		if (!def2.getUngroupedRoles().stream().allMatch(
				role2 -> def1.getUngroupedRoles().stream().anyMatch(role1 -> isSubRoleOfEntailed(role1, role2))))
			return false;
//		LOG.info("rg1:" + def1.getRoleGroups());
//		LOG.info("rg2:" + def2.getRoleGroups());
		if (!def2.getRoleGroups().stream()
				.allMatch(rg2 -> def1.getRoleGroups().stream().anyMatch(rg1 -> isSubsumedBy(rg1, rg2))))
			return false;
		return true;
	}

}
