/*
 * Copyright © 2023 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ikm.elk.snomed;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.OWLAPIConfigProvider;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.functional.parser.OWLFunctionalSyntaxOWLParser;
import org.semanticweb.owlapi.io.StreamDocumentSource;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataHasValueTest {

	private static final Logger LOG = LoggerFactory.getLogger(DataHasValueTest.class);

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

//	@Test
	public void dataHasValueStructure() throws Exception {
		OWLReasoner reasoner = loadAndClassify("/DataHasValueStructure.owl");
		for (OWLClass clazz : reasoner.getRootOntology().getClassesInSignature()) {
			for (OWLClass sup : reasoner.getSuperClasses(clazz, true).getFlattened()) {
				LOG.info("SUP: " + clazz + " " + sup);
			}
		}
	}

	@Test
	public void dataHasValue() throws Exception {
		OWLReasoner reasoner = loadAndClassify("/DataHasValue.owl");
		for (OWLClass clazz : reasoner.getRootOntology().getClassesInSignature()) {
			for (OWLClass sup : reasoner.getSuperClasses(clazz, true).getFlattened()) {
				LOG.info("SUP: " + clazz.getIRI().getShortForm() + " " + sup.getIRI().getShortForm());
			}
		}
	}

}
