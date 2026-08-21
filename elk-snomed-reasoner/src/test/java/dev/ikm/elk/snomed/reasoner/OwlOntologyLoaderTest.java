/*-
 * #%L
 * ELK Reasoner for SNOMED
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
package dev.ikm.elk.snomed.reasoner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.DummyProgressMonitor;
import org.semanticweb.elk.util.concurrent.computation.DummyInterruptMonitor;

import dev.ikm.elk.snomed.owlapix.model.OwlxOntology;

/**
 * A drained {@link OwlOntologyLoader} must stay finished when the ontology's
 * axiom set is mutated afterwards, as incremental updates do between
 * classifications. The loader used to capture a live iterator over the axiom
 * set; later additions made the exhausted iterator report {@code hasNext()}
 * again, so the loader resumed and walked off the end of the set's hash table
 * with an {@link ArrayIndexOutOfBoundsException}.
 */
public class OwlOntologyLoaderTest {

	private ElkAxiom subClassOfAxiom(OwlxOntology ontology, long sub, long sup) {
		return ontology.getSubClassOfAxiom("" + sub, ontology.getElkClass("" + sup));
	}

	private OwlOntologyLoader createAndDrainLoader(OwlxOntology ontology, List<ElkAxiom> inserted) {
		OwlOntologyLoader loader = new OwlOntologyLoader(DummyInterruptMonitor.INSTANCE, ontology,
				new DummyProgressMonitor());
		loader.load(inserted::add, ax -> {
		});
		return loader;
	}

	@Test
	public void loaderStaysFinishedAfterAxiomAdded() {
		OwlxOntology ontology = new OwlxOntology();
		for (int i = 1; i <= 5; i++)
			ontology.addAxiom(subClassOfAxiom(ontology, 100 + i, 1));
		List<ElkAxiom> inserted = new ArrayList<>();
		OwlOntologyLoader loader = createAndDrainLoader(ontology, inserted);
		assertEquals(5, inserted.size());
		assertTrue(loader.isLoadingFinished());
		ontology.addAxiom(subClassOfAxiom(ontology, 200, 1));
		assertTrue(loader.isLoadingFinished(),
				"a drained loader must stay finished when the axiom set is mutated later");
	}

	@Test
	public void loadAfterAxiomsAddedIsNoOp() {
		OwlxOntology ontology = new OwlxOntology();
		for (int i = 1; i <= 300; i++)
			ontology.addAxiom(subClassOfAxiom(ontology, 100 + i, 1));
		List<ElkAxiom> inserted = new ArrayList<>();
		OwlOntologyLoader loader = createAndDrainLoader(ontology, inserted);
		assertEquals(300, inserted.size());
		for (int i = 1; i <= 50; i++)
			ontology.addAxiom(subClassOfAxiom(ontology, 5000 + i, 1));
		// Axioms added after the initial load reach the reasoner through the
		// changes loader; the initial-load loader must not resume.
		List<ElkAxiom> insertedAfter = new ArrayList<>();
		loader.load(insertedAfter::add, ax -> {
		});
		assertEquals(0, insertedAfter.size(),
				"a drained loader must not re-feed axioms after the axiom set is mutated");
	}

}
