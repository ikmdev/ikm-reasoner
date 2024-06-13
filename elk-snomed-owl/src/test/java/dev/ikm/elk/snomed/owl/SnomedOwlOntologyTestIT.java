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

public class SnomedOwlOntologyTestIT extends SnomedOwlOntologyReasonerUsTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOwlOntologyTestIT.class);

	protected String getVersion() {
		return "20210301";
	}

	{
		expected_axiom_cnt = 362861;
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		OWLOntology oo = ontology.getOntology();
		assertEquals(362853, oo.getAxiomCount());
		assertEquals(362853, oo.getLogicalAxiomCount());
		assertEquals(361462, oo.getSignature().size());
		assertEquals(361331, oo.getClassesInSignature().size());
		assertEquals(131, oo.getObjectPropertiesInSignature().size());
		assertEquals(5, oo.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF).size());
		oo.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF).forEach(x -> LOG.info("" + x));
		assertEquals(4, oo.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY).size());
		oo.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY).forEach(x -> LOG.info("" + x));
		assertEquals(2, oo.getAxioms(AxiomType.REFLEXIVE_OBJECT_PROPERTY).size());
		oo.getAxioms(AxiomType.REFLEXIVE_OBJECT_PROPERTY).forEach(x -> LOG.info("" + x));
		assertEquals(
				Set.of(AxiomType.SUBCLASS_OF, AxiomType.EQUIVALENT_CLASSES, AxiomType.SUB_OBJECT_PROPERTY,
						AxiomType.SUB_PROPERTY_CHAIN_OF, AxiomType.TRANSITIVE_OBJECT_PROPERTY,
						AxiomType.REFLEXIVE_OBJECT_PROPERTY),
				oo.getAxioms().stream().map(OWLAxiom::getAxiomType).distinct()
						.collect(Collectors.toCollection(HashSet::new)));
		assertEquals(0, oo.getDataPropertiesInSignature().size());
		assertEquals(0, oo.getDatatypesInSignature().size());
		assertEquals(1200,
				ontology.getOwlClasses().stream().filter(x -> ontology.getAxioms(x).size() != 1).toList().size());
		assertEquals(161, getGciCount(ontology));
		assertEquals(161, ontology.getGciAxioms().size());
		assertEquals(161, ontology.getOwlClasses().stream().mapToInt(x -> ontology.getGciAxioms(x).size()).sum());
		testSignature(ontology);
	}

}
