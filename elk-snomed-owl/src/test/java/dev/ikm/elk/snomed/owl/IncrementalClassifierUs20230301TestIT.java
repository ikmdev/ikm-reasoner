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

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementalClassifierUs20230301TestIT extends SnomedTestBase implements SnomedVersionUs {

	private static final Logger LOG = LoggerFactory.getLogger(IncrementalClassifierUs20230301TestIT.class);

	@Override
	public String getVersion() {
		return "20230301";
	}

	@Override
	public String getInternationalVersion() {
		return "20221231";
	}

	public SnomedOwlOntology classify() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		return ontology;
	}

	public Set<Long> setParent(long concept_id, long parent_id) throws Exception {
		SnomedOwlOntology ontology = classify();
		Set<OWLClassAxiom> axioms = ontology.getAxioms(ontology.getOwlClass(concept_id));
		LOG.info("Axioms: " + axioms);
		OWLOntologyManager mgr = ontology.getOntology().getOWLOntologyManager();
		mgr.removeAxioms(ontology.getOntology(), axioms);
		OWLSubClassOfAxiom ax = mgr.getOWLDataFactory().getOWLSubClassOfAxiom(ontology.getOwlClass(concept_id),
				ontology.getOwlClass(parent_id));
		mgr.addAxiom(ontology.getOntology(), ax);
		LOG.info("Flushing");
		ontology.getReasoner().flush();
		LOG.info("Flushed");
		Set<Long> sups = ontology.getSuperClasses(concept_id);
		LOG.info("Sups: " + sups);
		return sups;
	}

	// 123823007 |Decreased blood oxygen pressure (finding)|
	// 123822002 |Increased blood oxygen pressure (finding)|

	private final long decreased_id = 123823007;
	private final long increased_id = 123822002;

	@Test
	public void change() throws Exception {
		Set<Long> sups = setParent(increased_id, decreased_id);
		assertEquals(Set.of(decreased_id), sups);
	}

	// 307824009 |Administrative statuses (finding)|
	// 138875005 |SNOMED CT Concept (SNOMED RT+CTV3)|

	private final long admin_id = 307824009;
	private final long snomed_id = 138875005;

	@Test
	public void changeBig() throws Exception {
		Set<Long> sups = setParent(admin_id, snomed_id);
		assertEquals(Set.of(snomed_id), sups);
	}

}
