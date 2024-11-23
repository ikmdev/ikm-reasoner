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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.OWLAPIConfigProvider;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.functional.parser.OWLFunctionalSyntaxOWLParser;
import org.semanticweb.owlapi.io.StreamDocumentSource;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataHasValueTest {

	private static final Logger LOG = LoggerFactory.getLogger(DataHasValueTest.class);

	public static PrefixManager pm = new DefaultPrefixManager(null, null, "");

	public OWLReasoner loadAndClassify(String file) throws Exception {
		OWLOntologyManager mgr = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = mgr.createOntology();
		OWLOntologyLoaderConfiguration config = new OWLAPIConfigProvider().get();
		InputStream is = this.getClass().getResourceAsStream(file);
		new OWLFunctionalSyntaxOWLParser().parse(new StreamDocumentSource(is), ontology, config);
		LOG.info("Axioms: " + ontology.getAxiomCount());
		LOG.info("Create reasoner");
		OWLReasonerFactory rf = (OWLReasonerFactory) new ElkReasonerFactory();
		OWLReasoner reasoner = rf.createReasoner(ontology);
		reasoner.flush();
		LOG.info("Compute inferences");
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		LOG.info("Done");
		return reasoner;
	}

	private OWLReasoner loadClassifyPrint(String file) throws Exception {
		LOG.info(file);
		OWLReasoner reasoner = loadAndClassify(file);
		for (OWLClass clazz : reasoner.getRootOntology().getClassesInSignature()) {
			LOG.info("Cl: " + clazz.getIRI());
			for (OWLClass sup : reasoner.getSuperClasses(clazz, true).getFlattened()) {
				LOG.info("Sup: " + clazz.getIRI().getShortForm() + " " + sup.getIRI().getShortForm());
			}
		}
		return reasoner;
	}

	public void loadClassifyPrintTest(String file) throws Exception {
		OWLReasoner reasoner = loadClassifyPrint(file);
		OWLDataFactory df = reasoner.getRootOntology().getOWLOntologyManager().getOWLDataFactory();
		OWLClass a1 = df.getOWLClass(":A1", pm);
		OWLClass a2 = df.getOWLClass(":A2", pm);
		LOG.info("A2: " + a2.getIRI());
		LOG.info("A2: " + a2.getIRI());
		for (OWLClass sup : reasoner.getSuperClasses(a2, true).getFlattened()) {
			LOG.info("Sup: " + sup.getIRI());
		}
		LOG.info("En: " + reasoner.isEntailed(df.getOWLSubClassOfAxiom(a2, a1)));
		LOG.info("En: " + reasoner.isEntailed(df.getOWLSubClassOfAxiom(a1, a2)));
		assertTrue(reasoner.isEntailed(df.getOWLSubClassOfAxiom(a2, a1)));
		assertFalse(reasoner.isEntailed(df.getOWLSubClassOfAxiom(a1, a2)));
	}

//	@Test
	public void dataHasValue() throws Exception {
		loadClassifyPrintTest("/DataHasValue.owl");
	}

//	@Test
	public void dataHasValueAsClass() throws Exception {
		loadClassifyPrintTest("/DataHasValueAsClass.owl");
	}

	@Test
	public void dataHasValueAsIndividual() throws Exception {
		loadClassifyPrintTest("/DataHasValueAsIndividual.owl");
	}

}
