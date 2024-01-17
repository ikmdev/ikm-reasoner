package dev.ikm.elk.snomed;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedOwlOntologyTest {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOwlOntologyTest.class);

	private final String home = System.getProperty("user.home");

	private final String dir = "data/snomed/SnomedCT_ManagedServiceUS_PRODUCTION_US1000124_20230301T120000Z/Snapshot/Terminology/";

	private final Path axioms_file = Paths.get(home, dir, "sct2_sRefset_OWLExpressionSnapshot_US1000124_20230301.txt");

	private final Path rels_file = Paths.get(home, dir, "sct2_Relationship_Snapshot_US1000124_20230301.txt");

	@BeforeEach
	private void filesExist() {
		assumeTrue(Files.exists(axioms_file), "No file: " + axioms_file);
		assumeTrue(Files.exists(rels_file), "No file: " + rels_file);
	}

	@Test
	public void readAxioms() throws Exception {
		List<String> axioms = SnomedOwlOntology.readAxioms(axioms_file);
		assertEquals(369237, axioms.size());
		axioms.stream().map(ax -> ax.substring(0, ax.indexOf("("))).distinct().sorted().forEach(LOG::info);
	}

	@Test
	public void readOntology() throws Exception {
		List<String> axioms = SnomedOwlOntology.readOntology(axioms_file);
		assertEquals(369238, axioms.size());
		axioms.stream().limit(10).forEach(LOG::info);
		LOG.info("...");
		axioms.stream().skip(axioms.size() - 5).forEach(LOG::info);
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		assertEquals(369230, ontology.getOntology().getAxiomCount());
		assertEquals(367704, ontology.getOntology().getSignature().size());
		assertEquals(367565, ontology.getOntology().getClassesInSignature().size());
		assertEquals(126, ontology.getOntology().getObjectPropertiesInSignature().size());
		assertEquals(11, ontology.getOntology().getDataPropertiesInSignature().size());
		assertEquals(2, ontology.getOntology().getDatatypesInSignature().size());
		Set<OWLEntity> sig = ontology.getOntology().getSignature();
		sig.removeAll(ontology.getOntology().getClassesInSignature());
		sig.removeAll(ontology.getOntology().getObjectPropertiesInSignature());
		sig.removeAll(ontology.getOntology().getDataPropertiesInSignature());
		sig.stream().forEach(x -> LOG.info("In sig: " + x + " " + x.getClass()));
		sig.removeAll(ontology.getOntology().getDatatypesInSignature());
		assertEquals(0, sig.size());
	}

	@Test
	public void classify() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
	}

	@Test
	public void readIsa() throws Exception {
		HashMap<Long, Set<Long>> isas = SnomedOwlOntology.readIsa(rels_file);
		assertEquals(367699, isas.size());
	}

	private HashMap<Long, Set<Long>> isas;

	private boolean hasAncestor(long con, long ancestor) {
		if (isas.get(con) == null)
			return false;
		if (isas.get(con).contains(ancestor))
			return true;
		for (long parent : isas.get(con)) {
			if (hasAncestor(parent, ancestor))
				return true;
		}
		return false;
	}

	@Test
	public void isas() throws Exception {
		TreeSet<Long> misses = new TreeSet<>();
		int miss_cnt = 0;
		int pharma_cnt = 0;
		int other_cnt = 0;
		isas = SnomedOwlOntology.readIsa(rels_file);
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		for (OWLClass clazz : ontology.getOntology().getClassesInSignature()) {
			long id = SnomedOwlOntology.getId(clazz);
			Set<Long> sups = ontology.getSuperClasses(id);
			Set<Long> parents = isas.get(id);
			// 138875005 |SNOMED CT Concept (SNOMED RT+CTV3)|
			if (id == 138875005) {
				assertNull(parents);
				parents = Set.of();
			} else {
				assertNotNull(parents);
			}
			if (!parents.equals(sups)) {
				misses.add(id);
				miss_cnt++;
				if (hasAncestor(id, 373873005)) {
					// 373873005 |Pharmaceutical / biologic product (product)|
					pharma_cnt++;
				} else if (hasAncestor(id, 127785005)) {
					// 127785005 |Administration of substance to produce immunity, either active or
					// passive (procedure)|
				} else if (hasAncestor(id, 713404003)) {
					// 713404003 |Vaccination given (situation)|
				} else if (hasAncestor(id, 591000119102l)) {
					// 591000119102 |Vaccine declined by patient (situation)|
				} else if (hasAncestor(id, 90351000119108l)) {
					// 90351000119108 |Vaccination not done (situation)|
				} else if (hasAncestor(id, 293104008)) {
					// 293104008 |Adverse reaction to component of vaccine product (disorder)|
				} else if (hasAncestor(id, 266758009)) {
					// 266758009 |Immunization contraindicated (situation)|
				} else {
					other_cnt++;
				}
			}
		}
		misses.stream().limit(10).forEach((id) -> {
			LOG.error("Miss: " + id);
			Set<Long> sups = ontology.getSuperClasses(id);
			Set<Long> parents = isas.get(id);
			HashSet<Long> par = new HashSet<>(parents);
			par.removeAll(sups);
			HashSet<Long> sup = new HashSet<>(sups);
			sup.removeAll(parents);
			LOG.error("Sno:  " + par);
			LOG.error("Elk:  " + sup);
		});
		LOG.error("Miss cnt: " + miss_cnt);
		LOG.error("Pharma cnt: " + pharma_cnt);
		LOG.error("Other cnt: " + other_cnt);
		assertEquals(0, other_cnt);
	}

}
