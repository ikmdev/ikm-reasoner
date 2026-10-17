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
import dev.ikm.elk.snomed.model.NestedRole;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleType;

/**
 * Existential restrictions whose fillers are expressions, ∃R.(∃S.C), classified as ELK reads
 * them.
 */
public class SnomedOntologyReasonerNestedRoleTest {

	@Test
	public void aNestedRestrictionClassifiesThroughItsFiller() {
		// A ⊑ ∃R.(B ⊓ ∃S.C); ∃R.∃S.C ⊑ D (D's GCI): A ⊑ D.
		RoleType r = new RoleType(11), s = new RoleType(12);
		Concept b = new Concept(2), c = new Concept(3);
		Definition aFiller = new Definition();
		aFiller.addSuperConcept(b);
		aFiller.addUngroupedRole(new Role(s, c));
		Concept a = new Concept(1);
		Definition aDef = new Definition();
		aDef.setDefinitionType(DefinitionType.SubConcept);
		aDef.addNestedRole(new NestedRole(r, aFiller));
		a.addDefinition(aDef);

		Definition dFiller = new Definition();
		dFiller.addUngroupedRole(new Role(s, c));
		Concept d = new Concept(4);
		Definition gci = new Definition();
		gci.setDefinitionType(DefinitionType.SubConcept);
		gci.addNestedRole(new NestedRole(r, dFiller));
		d.addGciDefinition(gci);

		SnomedOntology ontology = new SnomedOntology(List.of(a, b, c, d), List.of(r, s), List.of());
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(ontology);
		assertEquals(Set.of(d), reasoner.getSuperConcepts(a));
	}
}
