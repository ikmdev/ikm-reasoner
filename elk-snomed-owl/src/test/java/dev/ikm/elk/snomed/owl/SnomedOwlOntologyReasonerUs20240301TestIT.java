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

public class SnomedOwlOntologyReasonerUs20240301TestIT extends SnomedOwlOntologyReasonerTestBase
		implements SnomedVersionUs {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOwlOntologyReasonerUs20240301TestIT.class);

	@Override
	public String getVersion() {
		return "20240301";
	}

	@Override
	public String getInternationalVersion() {
		return "20240101";
	}

	{
		expected_axiom_cnt = 374216;
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		OWLOntology oo = ontology.getOntology();
		assertEquals(374209, oo.getAxiomCount());
		assertEquals(372528, oo.getSignature().size());
		assertEquals(372389, oo.getClassesInSignature().size());
		assertEquals(126, oo.getObjectPropertiesInSignature().size());
		assertEquals(125, oo.getAxioms(AxiomType.SUB_OBJECT_PROPERTY).size());
		assertEquals(5, oo.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF).size());
		oo.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF).forEach(x -> LOG.info("" + x));
		assertEquals(4, oo.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY).size());
		oo.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY).forEach(x -> LOG.info("" + x));
		assertEquals(2, oo.getAxioms(AxiomType.REFLEXIVE_OBJECT_PROPERTY).size());
		oo.getAxioms(AxiomType.REFLEXIVE_OBJECT_PROPERTY).forEach(x -> LOG.info("" + x));
		assertEquals(11, oo.getDataPropertiesInSignature().size());
		assertEquals(2, oo.getDatatypesInSignature().size());
		assertEquals(10, oo.getAxioms(AxiomType.SUB_DATA_PROPERTY).size());
//		oo.getAxioms(AxiomType.SUB_DATA_PROPERTY).forEach(x -> LOG.info("" + x));
		assertEquals(
				Set.of(AxiomType.SUBCLASS_OF, AxiomType.EQUIVALENT_CLASSES, AxiomType.SUB_OBJECT_PROPERTY,
						AxiomType.SUB_PROPERTY_CHAIN_OF, AxiomType.TRANSITIVE_OBJECT_PROPERTY,
						AxiomType.REFLEXIVE_OBJECT_PROPERTY, AxiomType.SUB_DATA_PROPERTY),
				oo.getAxioms().stream().map(OWLAxiom::getAxiomType).distinct()
						.collect(Collectors.toCollection(HashSet::new)));
		testSignature(ontology);
	}

}
