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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedNecessaryNormalFormInternationalTestBase extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedNecessaryNormalFormInternationalTestBase.class);

	protected String getDir() {
		return "data/snomed/SnomedCT_InternationalRF2_PRODUCTION_" + getVersion() + "T120000Z/Snapshot/Terminology/";
	}

	protected String getEdition() {
		return "INT";
	}

	public NecessaryNormalFormBuilder generate() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		LOG.info("Classify complete");
		NecessaryNormalFormBuilder nnfb = new NecessaryNormalFormBuilder(ontology);
		nnfb.init();
		LOG.info("Init complete");
		NecessaryNormalFormTest.checkPriors(ontology, nnfb);
		SnomedRoles roles = SnomedRoles.init(rels_file);
		LOG.info("Generate");
		long beg = System.currentTimeMillis();
		nnfb.generate(roles);
		LOG.info("Generate in " + ((System.currentTimeMillis() - beg) / 1000));
		return nnfb;
	}

}
