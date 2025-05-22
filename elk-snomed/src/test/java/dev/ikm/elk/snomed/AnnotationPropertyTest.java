package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2025 Integrated Knowledge Management
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.owlel.OwlElOntology;

public class AnnotationPropertyTest {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AnnotationPropertyTest.class);

	@Test
	public void annotationProperty() throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("src/test/resources/AnnotationProperty.owl"));
		OwlElOntology ontology = new OwlElOntology();
		ontology.load(lines);
		SnomedOntology snomedOntology = new OwlElTransformer().transform(ontology);
		assertEquals(3, snomedOntology.getAnnotationTypes().size());
		snomedOntology.getAnnotationTypes().forEach(at -> assertNotNull(snomedOntology.getAnnotationType(at.getId())));
	}

}
