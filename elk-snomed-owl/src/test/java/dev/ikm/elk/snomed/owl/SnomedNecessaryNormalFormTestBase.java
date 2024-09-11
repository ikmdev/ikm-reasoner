package dev.ikm.elk.snomed.owl;

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

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.ConceptComparer;
import dev.ikm.elk.snomed.NecessaryNormalFormBuilder;
import dev.ikm.elk.snomed.SnomedConcreteRoles;
import dev.ikm.elk.snomed.SnomedOntology;
import dev.ikm.elk.snomed.SnomedOntologyReasoner;
import dev.ikm.elk.snomed.SnomedRoles;

public abstract class SnomedNecessaryNormalFormTestBase extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedNecessaryNormalFormTestBase.class);

	@Test
	public void checkPriors() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		SnomedOntology snomedOntology = new OwlTransformer().transform(ontology);
		SnomedOntologyReasoner snomedOntologyReasoner = SnomedOntologyReasoner.create(snomedOntology);
		snomedOntologyReasoner.flush();
		NecessaryNormalFormBuilder nnfb = NecessaryNormalFormBuilder.create(snomedOntology,
				snomedOntologyReasoner.getSuperConcepts(), snomedOntologyReasoner.getSuperRoleTypes(false));
		NecessaryNormalFormTest.checkPriors(ontology, nnfb);
	}

	public NecessaryNormalFormBuilder generate() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		LOG.info("Load complete");
		//
		for (OWLAxiom ax : ontology.getOntology().getAxioms()) {
			switch (ax.getAxiomType().getName()) {
			case "SubClassOf" -> {
			}
			case "EquivalentClasses" -> {
			}
			case "SubObjectPropertyOf" -> {
			}
			case "SubPropertyChainOf" -> {
			}
			case "TransitiveObjectProperty" -> {
			}
			case "ReflexiveObjectProperty" -> {
			}
			case "SubDataPropertyOf" -> {
			}
			default -> throw new UnsupportedOperationException("Unexpected: " + ax + " " + ax.getAxiomType());
			}
		}
		//
		SnomedOntology snomedOntology = new OwlTransformer().transform(ontology);
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

}
