package dev.ikm.elk.snomed.owlel.parser;

/*-
 * #%L
 * elk-snomed-owl-el
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

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.owlel.OwlElOntology;

public class OwlElOntologyTestIT {

	private static final Logger LOG = LoggerFactory.getLogger(OwlElOntologyTestIT.class);

	@Test
	public void load() throws IOException {
		OwlElOntology ontology = new OwlElOntology();
		ontology.load(SnomedOfsParserTestIT.file);
		assertEquals("http://snomed.info/sct/900000000000207008", ontology.getIri());
		assertEquals(369438, ontology.getAxioms().size());
	}

}
