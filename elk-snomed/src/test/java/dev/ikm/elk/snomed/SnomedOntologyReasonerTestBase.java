package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration with SNOMED
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.RoleType;
import dev.ikm.elk.snomed.owlel.OwlElOntology;

public abstract class SnomedOntologyReasonerTestBase extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOntologyReasonerTestBase.class);

	@Test
	public void classify() throws Exception {
		OwlElOntology ontology = new OwlElOntology();
		ontology.load(axioms_file);
		SnomedOntology snomedOntology = new OwlElTransformer().transform(ontology);
		SnomedOntologyReasoner sor = SnomedOntologyReasoner.create(snomedOntology);
		for (RoleType rt : snomedOntology.getRoleTypes()) {
			if (!rt.getSuperRoleTypes().stream().map(RoleType::getId).toList()
					.equals(List.of(SnomedIds.concept_model_object_attribute))) {
				LOG.info("" + rt);
				rt.getSuperRoleTypes().forEach(sup -> LOG.info("\tSup 1: " + sup));
				sor.getSuperObjectProperties(rt).forEach(sup -> LOG.info("\tSup 2: " + sup));
			}
			if (rt.isTransitive())
				LOG.info("Transitive: " + rt);
			if (rt.getChained() != null)
				LOG.info("Chained: " + rt + " " + rt.getChained());
			if (rt.isReflexive())
				LOG.info("Reflexive: " + rt);
		}
		{
			ArrayList<Long> misses = new ArrayList<>();
			int miss_cnt = 0;
			SnomedIsa isas = SnomedIsa.init(rels_file);
			for (Long id : isas.getOrderedConcepts()) {
				Concept con = snomedOntology.getConcept(id);
				if (isas.hasAncestor(id, SnomedIds.concept_model_object_attribute))
					continue;
				if (con == null) {
					LOG.info("Skipping: " + id);
					continue;
				}
				Set<Long> sups = sor.getSuperClasses(con).stream()
						.map(cl -> Long.parseLong(cl.getIri().toString().substring(1)))
						.collect(Collectors.toCollection(HashSet::new));
				Set<Long> parents = isas.getParents(id);
				if (id == SnomedIds.root) {
					assertTrue(parents.isEmpty());
				} else {
					assertNotNull(parents);
				}
				if (!parents.equals(sups)) {
					misses.add(id);
					miss_cnt++;
					LOG.info("Miss: " + con);
					HashSet<Long> extra = new HashSet<Long>(sups);
					extra.removeAll(parents);
					for (long x : extra) {
						LOG.info("\tExtra: " + x);
					}
					HashSet<Long> missing = new HashSet<Long>(parents);
					missing.removeAll(sups);
					for (long x : missing) {
						LOG.info("\tMissing: " + x);
					}
				}
			}
			LOG.info("Miss: " + miss_cnt);
			assertEquals(0, miss_cnt);
		}
	}

}
