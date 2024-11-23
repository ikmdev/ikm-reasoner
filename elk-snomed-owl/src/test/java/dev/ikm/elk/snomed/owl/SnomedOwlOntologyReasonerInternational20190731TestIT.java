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

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedOwlOntologyReasonerInternational20190731TestIT extends SnomedOwlOntologyReasonerTestBase
		implements SnomedVersionInternational {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory
			.getLogger(SnomedOwlOntologyReasonerInternational20190731TestIT.class);

	@Override
	public String getVersion() {
		return "20190731";
	}

	{
		expected_axiom_cnt = 351059;
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		assertEquals(351052, ontology.getOntology().getAxiomCount());
		assertEquals(350831, ontology.getOntology().getSignature().size());
		assertEquals(350711, ontology.getOntology().getClassesInSignature().size());
		assertEquals(120, ontology.getOntology().getObjectPropertiesInSignature().size());
		assertEquals(0, ontology.getOntology().getDataPropertiesInSignature().size());
		assertEquals(0, ontology.getOntology().getDatatypesInSignature().size());
		testSignature(ontology);
	}

}
