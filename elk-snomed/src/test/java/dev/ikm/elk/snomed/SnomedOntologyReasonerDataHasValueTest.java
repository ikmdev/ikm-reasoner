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

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.owlel.OwlElOntology;

public class SnomedOntologyReasonerDataHasValueTest {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOntologyReasonerDataHasValueTest.class);

	public ConcreteRole classify(String to) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("src/test/resources/DataHasValue.owl"));
		List<String> axioms = lines.stream().map(line -> line.replace("\"1\"^^xsd:integer", to)).toList();
		OwlElOntology ontology = new OwlElOntology();
		ontology.load(axioms);
		SnomedOntology snomedOntology = new OwlElTransformer().transform(ontology);
		SnomedOntologyReasoner sor = SnomedOntologyReasoner.create(snomedOntology);
		
		// Convert primitive MutableLongSet to Set<Long> for comparison
		MutableLongSet superConcepts = sor.getSuperConcepts(2);
		Set<Long> superConceptsSet = superConcepts.collect(Long::valueOf).toSet();
		assertEquals(Set.of(1L), superConceptsSet);
		
		return snomedOntology.getConcept(1).getDefinitions().getFirst().getUngroupedConcreteRoles().iterator().next();
	}

	@Test
	public void dataHasBooleanValue() throws Exception {
		ConcreteRole role = classify("\"true\"^^xsd:boolean");
		assertEquals(ConcreteRole.ValueType.Boolean, role.getValueType());
		assertEquals(true, Boolean.valueOf(role.getValue()));
		LOG.info(""+ role);
	}

	@Test
	public void dataHasDecimalValue() throws Exception {
		ConcreteRole role = classify("\"1.2\"^^xsd:decimal");
		assertEquals(ConcreteRole.ValueType.Decimal, role.getValueType());
		assertEquals(new BigDecimal("1.2"), new BigDecimal(role.getValue()));
	}

	@Test
	public void dataHasDoubleValue() throws Exception {
		ConcreteRole role = classify("\"1.2e10\"^^xsd:double");
		assertEquals(ConcreteRole.ValueType.Double, role.getValueType());
		assertEquals(1.2e10, Double.valueOf(role.getValue()));
	}

	@Test
	public void dataHasFloatValue() throws Exception {
		ConcreteRole role = classify("\"1.2e5\"^^xsd:float");
		assertEquals(ConcreteRole.ValueType.Float, role.getValueType());
		assertEquals(1.2e5f, Float.valueOf(role.getValue()));
	}

	@Test
	public void dataHasIntegerValue() throws Exception {
		ConcreteRole role = classify("\"1\"^^xsd:integer");
		assertEquals(ConcreteRole.ValueType.Integer, role.getValueType());
		assertEquals(1, Integer.valueOf(role.getValue()));
	}
	
	@Test
	public void dataHasLongValue() throws Exception {
		ConcreteRole role = classify("\"112233445566\"^^xsd:long");
		assertEquals(ConcreteRole.ValueType.Long, role.getValueType());
		assertEquals(112233445566l, Long.valueOf(role.getValue()));
	}

	@Test
	public void dataHasStringValue() throws Exception {
		ConcreteRole role = classify("\"foo\"^^xsd:string");
		assertEquals(ConcreteRole.ValueType.String, role.getValueType());
		assertEquals("foo", role.getValue());
	}

}
