package dev.ikm.elk.snomed;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*-
 * #%L
 * ELK Integration with SNOMED
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

public class SnomedOntologyReasonerNebraskaTestIT extends SnomedOntologyReasonerTestBase implements SnomedVersion {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(SnomedOntologyReasonerNebraskaTestIT.class);

	@Override
	public String getEdition() {
		return "1000004";
	}

	@Override
	public String getEditionDir() {
		return "nebraska";
	}

	@Override
	public String getVersion() {
		return "20250129";
	}

	@Override
	public String getInternationalVersion() {
		return "20240801";
	}

	@BeforeAll
	public static void transform() throws Exception {
		Path source_dir = Paths.get(System.getProperty("user.home"),
				"projects/fda/nebraska/SNOMEDCT_RF2_BETA_20250129T190000Z", "Full/Terminology");
		Path target_dir = Paths.get("target/data/snomed-test-data-nebraska-20250129");
		new FullReleaseSnapshotTransformer().transformDirectory(source_dir, target_dir);
	}

	@Disabled
	public void versionClass() throws IOException {

	}

	@Disabled
	public void classify() throws Exception {
	}

}
