package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration Testing with SNOMED
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedOwlOntologyTest extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOwlOntologyTest.class);

	protected String getDir() {
		return "data/snomed/SnomedCT_USEditionRF2_PRODUCTION_20210301T120000Z/Snapshot/Terminology/";
	}

	protected String getVersion() {
		return "20210301";
	}

	@Test
	public void readAxioms() throws Exception {
		List<String> axioms = SnomedOwlOntology.readAxioms(axioms_file);
		assertEquals(362861, axioms.size());
		axioms.stream().map(ax -> ax.substring(0, ax.indexOf("("))).distinct().sorted().forEach(LOG::info);
	}

	@Test
	public void readOntology() throws Exception {
		List<String> axioms = SnomedOwlOntology.readOntology(axioms_file);
		assertEquals(362862, axioms.size());
		axioms.stream().limit(10).forEach(LOG::info);
		LOG.info("...");
		axioms.stream().skip(axioms.size() - 5).forEach(LOG::info);
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		assertEquals(362853, ontology.getOntology().getAxiomCount());
		assertEquals(361462, ontology.getOntology().getSignature().size());
		assertEquals(361331, ontology.getOntology().getClassesInSignature().size());
		assertEquals(131, ontology.getOntology().getObjectPropertiesInSignature().size());
		assertEquals(0, ontology.getOntology().getDataPropertiesInSignature().size());
		assertEquals(0, ontology.getOntology().getDatatypesInSignature().size());
		Set<OWLEntity> sig = ontology.getOntology().getSignature();
		sig.removeAll(ontology.getOntology().getClassesInSignature());
		sig.removeAll(ontology.getOntology().getObjectPropertiesInSignature());
		sig.removeAll(ontology.getOntology().getDataPropertiesInSignature());
		sig.stream().forEach(x -> LOG.info("In sig: " + x + " " + x.getClass()));
		sig.removeAll(ontology.getOntology().getDatatypesInSignature());
		assertEquals(0, sig.size());
		assertEquals(1200, ontology.getOntology().getClassesInSignature().stream()
				.filter(x -> ontology.getAxioms(x).size() != 1).toList().size());
	}

	@Test
	public void classify() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
	}

	@Test
	public void isas() throws Exception {
		TreeSet<Long> misses = new TreeSet<>();
		int miss_cnt = 0;
		SnomedIsa isas = SnomedIsa.init(rels_file);
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		for (OWLClass clazz : ontology.getOntology().getClassesInSignature()) {
			long id = SnomedOwlOntology.getId(clazz);
			Set<Long> sups = ontology.getSuperClasses(id);
			Set<Long> parents = isas.getParents(id);
			if (id == SnomedIsa.root) {
				assertTrue(parents.isEmpty());
			} else {
				assertNotNull(parents);
			}
			if (!parents.equals(sups)) {
				misses.add(id);
				miss_cnt++;
			}
		}
		assertEquals(0, miss_cnt);
	}

}
