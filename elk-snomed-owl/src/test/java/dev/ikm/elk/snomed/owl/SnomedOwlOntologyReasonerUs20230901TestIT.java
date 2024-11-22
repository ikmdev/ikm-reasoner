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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedOwlOntologyReasonerUs20230901TestIT extends SnomedOwlOntologyReasonerTestBase
		implements SnomedVersionUs {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOwlOntologyReasonerUs20230901TestIT.class);

	@Override
	public String getVersion() {
		return "20230901";
	}

	@Override
	public String getInternationalVersion() {
		return "20230630";
	}

	{
		expected_axiom_cnt = 371582;
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		OWLOntology oo = ontology.getOntology();
		assertEquals(371575, oo.getAxiomCount());
		assertEquals(370018, oo.getSignature().size());
		assertEquals(369879, oo.getClassesInSignature().size());
		assertEquals(126, oo.getObjectPropertiesInSignature().size());
		assertEquals(5, oo.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF).size());
		oo.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF).forEach(x -> LOG.info("" + x));
		assertEquals(4, oo.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY).size());
		oo.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY).forEach(x -> LOG.info("" + x));
		assertEquals(2, oo.getAxioms(AxiomType.REFLEXIVE_OBJECT_PROPERTY).size());
		oo.getAxioms(AxiomType.REFLEXIVE_OBJECT_PROPERTY).forEach(x -> LOG.info("" + x));
		assertEquals(
				Set.of(AxiomType.SUBCLASS_OF, AxiomType.EQUIVALENT_CLASSES, AxiomType.SUB_OBJECT_PROPERTY,
						AxiomType.SUB_PROPERTY_CHAIN_OF, AxiomType.TRANSITIVE_OBJECT_PROPERTY,
						AxiomType.REFLEXIVE_OBJECT_PROPERTY, AxiomType.SUB_DATA_PROPERTY),
				oo.getAxioms().stream().map(OWLAxiom::getAxiomType).distinct()
						.collect(Collectors.toCollection(HashSet::new)));
		assertEquals(11, oo.getDataPropertiesInSignature().size());
		assertEquals(2, oo.getDatatypesInSignature().size());
		testSignature(ontology);
	}

}
