package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.owlel.OwlElOntology;

public abstract class SnomedNecessaryNormalFormTestBase extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedNecessaryNormalFormTestBase.class);

	public int expected_concept_cnt = -1;

	public static void checkPriors(SnomedOntologyReasoner ontology, NecessaryNormalFormBuilder nnf) {
		HashSet<Long> priors = new HashSet<>();
		for (Concept con : nnf.getConcepts()) {
			for (Long sup : ontology.getSuperConcepts(con.getId())) {
				assertTrue(priors.contains(sup));
			}
			priors.add(con.getId());
		}
	}

	@Test
	public void checkPriors() throws Exception {
		OwlElOntology ontology = new OwlElOntology();
		ontology.load(axioms_file);
//		ontology.classify();
		SnomedOntology snomedOntology = new OwlElTransformer().transform(ontology);
		SnomedOntologyReasoner snomedOntologyReasoner = SnomedOntologyReasoner.create(snomedOntology);
		snomedOntologyReasoner.flush();
		NecessaryNormalFormBuilder nnfb = NecessaryNormalFormBuilder.create(snomedOntology,
				snomedOntologyReasoner.getSuperConcepts(), snomedOntologyReasoner.getSuperRoleTypes(false));
		checkPriors(snomedOntologyReasoner, nnfb);
	}

	private NecessaryNormalFormBuilder generate1() throws Exception {
		OwlElOntology ontology = new OwlElOntology();
		ontology.load(axioms_file);
		LOG.info("Load complete");
		SnomedOntology snomedOntology = new OwlElTransformer().transform(ontology);
		SnomedOntologyReasoner snomedOntologyReasoner = SnomedOntologyReasoner.create(snomedOntology);
		snomedOntologyReasoner.flush();
		NecessaryNormalFormBuilder nnfb = NecessaryNormalFormBuilder.create(snomedOntology,
				snomedOntologyReasoner.getSuperConcepts(), snomedOntologyReasoner.getSuperRoleTypes(false));
		LOG.info("Init complete");
		SnomedRoles roles = SnomedRoles.init(rels_file);
		SnomedConcreteRoles values = SnomedConcreteRoles.init(values_file);
		LOG.info("Generate");
		long beg = System.currentTimeMillis();
		ConceptComparer cc = new ConceptComparer(roles, values);
		nnfb.generate(cc);
		LOG.info("Generate in " + ((System.currentTimeMillis() - beg) / 1000));
		return nnfb;
	}

	@Test
	public void generate() throws Exception {
		NecessaryNormalFormBuilder nnfb = generate1();
		assertEquals(expected_concept_cnt, nnfb.getConcepts().size());
		assertEquals(0, nnfb.getMisMatchCount());
	}

}
