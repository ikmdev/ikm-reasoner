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
package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;

/**
 * Incremental classification after an initial full classification, as Komet's
 * incremental reasoner drives it: classify, author new concepts via
 * {@link SnomedOntologyReasoner#processUpdate(Concept)}, then
 * {@link SnomedOntologyReasoner#flush()}.
 * <p>
 * Before the {@code OwlOntologyLoader} fix, the flush failed with
 * {@code ArrayIndexOutOfBoundsException} from the loader's stale iterator over
 * the mutated axiom set (komet-desktop issue #92).
 */
public class SnomedOntologyReasonerIncrementalTest {

	private static final long ROOT = SnomedIds.root;

	private Concept createSubConceptOf(long id, Concept parent) {
		Concept con = new Concept(id);
		Definition def = new Definition();
		def.setDefinitionType(DefinitionType.SubConcept);
		def.addSuperConcept(parent);
		con.addDefinition(def);
		return con;
	}

	@Test
	public void newConceptsAfterInitialClassification() {
		Concept root = new Concept(ROOT);
		List<Concept> concepts = new ArrayList<>();
		concepts.add(root);
		for (int i = 1; i <= 300; i++)
			concepts.add(createSubConceptOf(1000 + i, root));
		SnomedOntology snomedOntology = new SnomedOntology(concepts, List.of(), List.of());
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(snomedOntology);
		assertEquals(Set.of(root), reasoner.getSuperConcepts(snomedOntology.getConcept(1001)));
		for (int i = 1; i <= 50; i++) {
			Concept con = createSubConceptOf(2000 + i, root);
			snomedOntology.addConcept(con);
			reasoner.processUpdate(con);
		}
		reasoner.flush();
		for (int i = 1; i <= 50; i++)
			assertEquals(Set.of(root), reasoner.getSuperConcepts(snomedOntology.getConcept(2000 + i)),
					"new concept " + (2000 + i) + " must classify under root");
	}

	@Test
	public void reparentedConceptAfterInitialClassification() {
		Concept root = new Concept(ROOT);
		Concept mid = createSubConceptOf(500, root);
		Concept leaf = createSubConceptOf(600, root);
		SnomedOntology snomedOntology = new SnomedOntology(List.of(root, mid, leaf), List.of(), List.of());
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(snomedOntology);
		assertEquals(Set.of(root), reasoner.getSuperConcepts(leaf));
		leaf.removeAllDefinitions();
		Definition def = new Definition();
		def.setDefinitionType(DefinitionType.SubConcept);
		def.addSuperConcept(mid);
		leaf.addDefinition(def);
		reasoner.processUpdate(leaf);
		reasoner.flush();
		assertEquals(Set.of(mid), reasoner.getSuperConcepts(leaf));
	}

}
