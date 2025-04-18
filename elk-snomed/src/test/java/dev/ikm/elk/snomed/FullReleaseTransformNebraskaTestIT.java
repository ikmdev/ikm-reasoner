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

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullReleaseTransformNebraskaTestIT {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(FullReleaseTransformNebraskaTestIT.class);

	@Test
	public void transform() throws Exception {
		Path source_dir = Paths.get(System.getProperty("user.home"),
				"projects/fda/nebraska/SNOMEDCT_RF2_BETA_20250129T190000Z", "Full/Terminology");
		Path target_dir = Paths.get("target/data/snomed-test-data-nebraska-20250129");
		new FullReleaseSnapshotTransformer().transformDirectory(source_dir, target_dir);
	}

}
