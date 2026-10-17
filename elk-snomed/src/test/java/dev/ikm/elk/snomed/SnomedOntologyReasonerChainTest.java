/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2026 Integrated Knowledge Management
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
package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleType;

/**
 * Property chains beyond SNOMED CT's right identity: any sequence of roles implying another,
 * R1 ∘ … ∘ Rn ⊑ R, as the ELK test corpus states them.
 */
public class SnomedOntologyReasonerChainTest {

	private static Concept concept(long id, Concept parent, Role... roles) {
		Concept con = new Concept(id);
		Definition def = new Definition();
		def.setDefinitionType(DefinitionType.SubConcept);
		if (parent != null)
			def.addSuperConcept(parent);
		for (Role role : roles)
			def.addUngroupedRole(role);
		con.addDefinition(def);
		return con;
	}

	@Test
	public void aChainImpliesItsRoleThroughNestedRestrictions() {
		// R ∘ S ⊑ T; A ⊑ ∃R.B, B ⊑ ∃S.C, ∃T.C ⊑ D (as D's GCI): A ⊑ D.
		RoleType r = new RoleType(11), s = new RoleType(12), t = new RoleType(13);
		t.addChain(List.of(r, s));
		Concept c = concept(3, null);
		Concept b = concept(2, null, new Role(s, c));
		Concept a = concept(1, null, new Role(r, b));
		Concept d = new Concept(4);
		Definition gci = new Definition();
		gci.setDefinitionType(DefinitionType.SubConcept);
		gci.addUngroupedRole(new Role(t, c));
		d.addGciDefinition(gci);
		SnomedOntology ontology = new SnomedOntology(List.of(a, b, c, d), List.of(r, s, t), List.of());
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(ontology);
		assertEquals(Set.of(d), reasoner.getSuperConcepts(a));
	}

	@Test
	public void aChainWithAReflexiveRoleMakesItsOtherRoleASubRole() {
		// R ∘ S ⊑ H with S reflexive: R ⊑ H (the ELK corpus's ChainWithReflexive), which ELK
		// leaves out of the role hierarchy unless the reasoner states it.
		RoleType r = new RoleType(11), s = new RoleType(12), h = new RoleType(13);
		s.setReflexive(true);
		h.addChain(List.of(r, s));
		SnomedOntology ontology = new SnomedOntology(List.of(), List.of(r, s, h), List.of());
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(ontology);
		assertEquals(Set.of(h), reasoner.getSuperRoleTypes(r));
	}

	@Test
	public void aSuperRoleOfAReflexiveRoleIsReflexiveInAChain() {
		// H reflexive, H ⊑ H1, R ∘ H1 ⊑ S: H1 is reflexive too, so R ⊑ S.
		RoleType r = new RoleType(11), h = new RoleType(12), h1 = new RoleType(13), s = new RoleType(14);
		h.setReflexive(true);
		h.addSuperRoleType(h1);
		s.addChain(List.of(r, h1));
		SnomedOntology ontology = new SnomedOntology(List.of(), List.of(r, h, h1, s), List.of());
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(ontology);
		assertEquals(Set.of(s), reasoner.getSuperRoleTypes(r));
	}
}
