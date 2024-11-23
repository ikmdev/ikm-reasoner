package dev.ikm.elk.snomed.owl;

/*-
 * #%L
 * ELK Integration with SNOMED using OWL API
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NecessaryNormalFormOwlTest {

	private static final Logger LOG = LoggerFactory.getLogger(NecessaryNormalFormOwlTest.class);

	public static void checkPriors(SnomedOwlOntology ontology, NecessaryNormalFormBuilderOwl nnf) {
		HashSet<OWLClass> priors = new HashSet<>();
		for (OWLClass con : nnf.getConcepts()) {
			for (OWLClass sup : ontology.getSuperClasses(con)) {
//				LOG.info(con + " " + sup);
				assertTrue(priors.contains(sup));
			}
			priors.add(con);
		}
	}

	private NecessaryNormalFormBuilderOwl load(SnomedOwlOntology ontology, String file) throws Exception {
		LOG.info("Load: " + file);
		List<String> lines = Files.readAllLines(Paths.get("src/test/resources", file));
		ontology.loadOntology(lines);
		ontology.classify();
		LOG.info("Classify complete");
		NecessaryNormalFormBuilderOwl nnfb = new NecessaryNormalFormBuilderOwl(ontology);
		nnfb.init();
		LOG.info("Init complete");
		checkPriors(ontology, nnfb);
		nnfb.generate();
		for (OWLClass con : nnfb.getConcepts()) {
			LOG.info("Con: " + con);
			if (nnfb.getNecessaryNormalForms(con) == null)
				continue;
			for (NecessaryNormalForm expr : nnfb.getNecessaryNormalForms(con)) {
				LOG.info("\t" + expr.isSubClassOf());
				expr.getSups().forEach(x -> LOG.info("\tSup: " + x));
				expr.getUngroupedProps().forEach(x -> LOG.info("\tProp: " + x));
				expr.getGroupedProps().forEach(x -> LOG.info("\tProp group: " + x));
			}
		}
		return nnfb;
	}

	@Test
	public void ungrouped() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilderOwl nnfb = load(ontology, "NecessaryNormalForm.owl");
		NecessaryNormalForm nnf;
		nnf = nnfb.getNecessaryNormalForms(ontology.getOwlClass(202)).getFirst();
		assertEquals(1, nnf.getUngroupedProps().size());
		assertEquals(ontology.getOwlClass(102), nnf.getUngroupedProps().iterator().next().getFiller());
		assertEquals(0, nnf.getGroupedProps().size());
		nnf = nnfb.getNecessaryNormalForms(ontology.getOwlClass(203)).getFirst();
		assertEquals(2, nnf.getUngroupedProps().size());
		assertEquals(0, nnf.getGroupedProps().size());
		nnf = nnfb.getNecessaryNormalForms(ontology.getOwlClass(204)).getFirst();
		assertEquals(2, nnf.getUngroupedProps().size());
		assertEquals(0, nnf.getGroupedProps().size());
	}

	@Test
	public void grouped() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilderOwl nnfb = load(ontology, "NecessaryNormalFormGrouped.owl");
		NecessaryNormalForm nnf = nnfb.getNecessaryNormalForms(ontology.getOwlClass(202)).getFirst();
		assertEquals(0, nnf.getUngroupedProps().size());
		assertEquals(1, nnf.getGroupedProps().size());
		Set<OWLObjectSomeValuesFrom> gr = nnf.getGroupedProps().iterator().next();
		assertEquals(1, gr.size());
		assertEquals(ontology.getOwlClass(102), gr.iterator().next().getFiller());
	}

	@Test
	public void subProperty() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilderOwl nnfb = load(ontology, "NecessaryNormalFormSubProperty.owl");
		NecessaryNormalForm nnf = nnfb.getNecessaryNormalForms(ontology.getOwlClass(202)).getFirst();
		assertEquals(1, nnf.getUngroupedProps().size());
		assertEquals(ontology.getOwlClass(102), nnf.getUngroupedProps().iterator().next().getFiller());
		assertEquals(0, nnf.getGroupedProps().size());
	}

	@Test
	public void propertyChain() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilderOwl nnfb = load(ontology, "NecessaryNormalFormPropertyChain.owl");
		NecessaryNormalForm nnf = nnfb.getNecessaryNormalForms(ontology.getOwlClass(202)).getFirst();
		assertEquals(1, nnf.getUngroupedProps().size());
		assertEquals(ontology.getOwlClass(102), nnf.getUngroupedProps().iterator().next().getFiller());
		assertEquals(0, nnf.getGroupedProps().size());
	}

}
