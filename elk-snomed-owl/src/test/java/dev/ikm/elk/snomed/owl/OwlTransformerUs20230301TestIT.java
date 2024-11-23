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

import dev.ikm.elk.snomed.SnomedOntology;
import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;

public class OwlTransformerUs20230301TestIT extends SnomedTestBase implements SnomedVersionUs {

	private static final Logger LOG = LoggerFactory.getLogger(OwlTransformerUs20230301TestIT.class);

	@Override
	public String getVersion() {
		return "20230301";
	}

	@Override
	public String getInternationalVersion() {
		return "20221231";
	}

	@Test
	public void transform() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
//		ontology.classify();
		LOG.info("Classify complete");
		OwlTransformer ot = new OwlTransformer();
		SnomedOntology so = ot.transform(ontology);
		LOG.info("Transform complete");
		assertEquals(367565, so.getConcepts().size());
		assertEquals(126, so.getRoleTypes().size());
		{
			// 86299006 |Tetralogy of Fallot (disorder)|
			Concept con = so.getConcept(86299006);
			Definition def = con.getDefinitions().getFirst();
			assertEquals(DefinitionType.EquivalentConcept, def.getDefinitionType());
			assertEquals(1, def.getSuperConcepts().size());
			assertEquals(0, def.getUngroupedRoles().size());
			assertEquals(0, def.getUngroupedConcreteRoles().size());
			assertEquals(4, def.getRoleGroups().size());
		}
		{
			// 784779008 |Product containing precisely erythromycin 250 milligram/1 each
			// conventional release oral capsule (clinical drug)|
			Concept con = so.getConcept(784779008);
			Definition def = con.getDefinitions().getFirst();
			assertEquals(DefinitionType.EquivalentConcept, def.getDefinitionType());
			assertEquals(1, def.getSuperConcepts().size());
			assertEquals(2, def.getUngroupedRoles().size());
			assertEquals(1, def.getUngroupedConcreteRoles().size());
			assertEquals(1, def.getRoleGroups().size());
			assertEquals(4, def.getRoleGroups().iterator().next().getRoles().size());
		}
		{
			// 10000006 |Radiating chest pain (finding)|
			Concept con = so.getConcept(10000006);
			Definition def = con.getDefinitions().getFirst();
			assertEquals(DefinitionType.SubConcept, def.getDefinitionType());
			assertEquals(2, def.getSuperConcepts().size());
			assertEquals(0, def.getUngroupedRoles().size());
			assertEquals(0, def.getUngroupedConcreteRoles().size());
			assertEquals(0, def.getRoleGroups().size());
		}
	}

}
