package dev.ikm.elk.snomed;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.RoleGroup;

public class NecessaryNormalFormTest {

	private static final Logger LOG = LoggerFactory.getLogger(NecessaryNormalFormTest.class);

	public static void checkPriors(SnomedOwlOntology ontology, NecessaryNormalFormBuilder nnf) {
		HashSet<Long> priors = new HashSet<>();
		for (Concept con : nnf.getConcepts()) {
//			LOG.info("Con: " + con);
			for (Long sup : ontology.getSuperClasses(con.getId())) {
//				LOG.info(con + " " + sup);
				assertTrue(priors.contains(sup));
			}
			priors.add(con.getId());
		}
	}

	private NecessaryNormalFormBuilder load(SnomedOwlOntology ontology, String file) throws Exception {
		LOG.info("Load: " + file);
		List<String> lines = Files.readAllLines(Paths.get("src/test/resources", file));
		ontology.loadOntology(lines);
		ontology.classify();
		LOG.info("Classify complete");
		NecessaryNormalFormBuilder nnfb = new NecessaryNormalFormBuilder(ontology);
		nnfb.init();
//		assertEquals(5, nnfb.getConcepts().size());
		LOG.info("Init complete");
		checkPriors(ontology, nnfb);
		nnfb.generate();
		for (Concept con : nnfb.getConcepts()) {
			LOG.info("Con: " + con.getId());
			if (nnfb.getNecessaryNormalForm(con) == null)
				continue;
			Definition expr = nnfb.getNecessaryNormalForm(con);
			LOG.info("\t" + expr.getDefinitionType());
			expr.getSuperConcepts().forEach(x -> LOG.info("\tSup: " + x.getId()));
			expr.getUngroupedRoles().forEach(x -> LOG.info("\t" + x));
			expr.getRoleGroups().forEach(x -> LOG.info("\t" + x));
		}
		return nnfb;
	}

	@Test
	public void ungrouped() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilder nnfb = load(ontology, "NecessaryNormalForm.owl");
		Definition nnf;
		nnf = nnfb.getNecessaryNormalForm(202);
		assertEquals(1, nnf.getUngroupedRoles().size());
		assertEquals(102, nnf.getUngroupedRoles().iterator().next().getConcept().getId());
		assertEquals(0, nnf.getRoleGroups().size());
		nnf = nnfb.getNecessaryNormalForm(203);
		assertEquals(2, nnf.getUngroupedRoles().size());
		assertEquals(0, nnf.getRoleGroups().size());
		nnf = nnfb.getNecessaryNormalForm(204);
		assertEquals(2, nnf.getUngroupedRoles().size());
		assertEquals(0, nnf.getRoleGroups().size());
	}

	@Test
	public void grouped() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilder nnfb = load(ontology, "NecessaryNormalFormGrouped.owl");
		Definition nnf = nnfb.getNecessaryNormalForm(202);
		assertEquals(0, nnf.getUngroupedRoles().size());
		assertEquals(1, nnf.getRoleGroups().size());
		RoleGroup rg = nnf.getRoleGroups().iterator().next();
		assertEquals(1, rg.getRoles().size());
		assertEquals(102, rg.getRoles().iterator().next().getConcept().getId());
	}

	@Test
	public void subProperty() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilder nnfb = load(ontology, "NecessaryNormalFormSubProperty.owl");
		Definition nnf = nnfb.getNecessaryNormalForm(202);
		assertEquals(1, nnf.getUngroupedRoles().size());
		assertEquals(102, nnf.getUngroupedRoles().iterator().next().getConcept().getId());
		assertEquals(0, nnf.getRoleGroups().size());
	}

	@Test
	public void propertyChain() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		NecessaryNormalFormBuilder nnfb = load(ontology, "NecessaryNormalFormPropertyChain.owl");
		Definition nnf = nnfb.getNecessaryNormalForm(202);
		assertEquals(1, nnf.getUngroupedRoles().size());
		assertEquals(102, nnf.getUngroupedRoles().iterator().next().getConcept().getId());
		assertEquals(0, nnf.getRoleGroups().size());
	}

}
