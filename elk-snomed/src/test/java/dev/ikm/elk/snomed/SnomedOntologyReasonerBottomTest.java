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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;

/**
 * Disjointness and the bottom concept: a concept that is a kind of two disjoint concepts is
 * unsatisfiable, equivalent to the bottom and placed where the bottom concept is stated.
 */
public class SnomedOntologyReasonerBottomTest {

	private static Definition necessary(Concept... parents) {
		Definition def = new Definition();
		def.setDefinitionType(DefinitionType.SubConcept);
		for (Concept parent : parents)
			def.addSuperConcept(parent);
		return def;
	}

	@Test
	public void aKindOfTwoDisjointConceptsIsUnsatisfiable() {
		Concept root = new Concept(1), bottom = new Concept(2), a = new Concept(3), b = new Concept(4),
				c = new Concept(5);
		bottom.addDefinition(necessary(root));
		c.addDefinition(necessary(root));
		b.addDefinition(necessary(c));
		Definition aDef = necessary(c);
		aDef.addDisjointConcept(c);
		a.addDefinition(aDef);
		SnomedOntology ontology = new SnomedOntology(List.of(root, bottom, a, b, c), List.of(), List.of());
		ontology.setBottomConceptId(2);
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(ontology);

		assertTrue(reasoner.isUnsatisfiable(a));
		assertFalse(reasoner.isUnsatisfiable(b));
		assertEquals(Set.of(a, bottom), reasoner.getEquivalentConcepts(a));
		assertEquals(Set.of(root), reasoner.getSuperConcepts(a), "placed where the bottom concept is stated");
		assertEquals(Set.of(b), reasoner.getSubConcepts(c), "no leaf lists the unsatisfiable concept as a child");
		assertEquals(Set.of(a, bottom, c), reasoner.getSubConcepts(root));
	}

	@Test
	public void aKindOfTheBottomIsUnsatisfiable() {
		Concept root = new Concept(1), bottom = new Concept(2), a = new Concept(3);
		bottom.addDefinition(necessary(root));
		a.addDefinition(necessary(bottom));
		SnomedOntology ontology = new SnomedOntology(List.of(root, bottom, a), List.of(), List.of());
		ontology.setBottomConceptId(2);
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(ontology);
		assertTrue(reasoner.isUnsatisfiable(a));
		assertEquals(Set.of(root), reasoner.getSuperConcepts(a));
	}
}
