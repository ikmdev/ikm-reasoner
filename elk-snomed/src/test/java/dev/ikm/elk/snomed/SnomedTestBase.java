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

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedTestBase.class);

	private final String home = System.getProperty("user.home");

	protected String getDir() {
		return "data/snomed/SnomedCT_USEditionRF2_PRODUCTION_20210301T120000Z/Snapshot/Terminology/";
	}

	protected String getEdition() {
		return "US1000124";
	}

	protected String getVersion() {
		return "20210301";
	}

	protected Path axioms_file = Paths.get(home, getDir(),
			"sct2_sRefset_OWLExpressionSnapshot_" + getEdition() + "_" + getVersion() + ".txt");

	protected Path rels_file = Paths.get(home, getDir(),
			"sct2_Relationship_Snapshot_" + getEdition() + "_" + getVersion() + ".txt");

	@BeforeEach
	private void filesExist() {
		assumeTrue(Files.exists(axioms_file), "No file: " + axioms_file);
		assumeTrue(Files.exists(rels_file), "No file: " + rels_file);
		LOG.info("Files exist");
		LOG.info("\t" + axioms_file);
		LOG.info("\t" + rels_file);
	}

}
