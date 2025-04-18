package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullReleaseAxiomTransformUs20240901TestIT extends SnomedTestBase implements SnomedVersionUs {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(FullReleaseAxiomTransformUs20240901TestIT.class);

	@Override
	public String getVersion() {
		return "20240901";
	}

	@Override
	public String getInternationalVersion() {
		return "20240701";
	}

	@Test
	public void readAxioms() throws Exception {
		String release_dir = "SnomedCT_ManagedServiceUS_PRODUCTION_US1000124_20240901T120000Z";
		List<String> full = new FullReleaseSnapshotTransformer()
				.transformFile(Paths.get(System.getProperty("user.home"), "data/snomed", release_dir,
						"Full/Terminology/sct2_sRefset_OWLExpressionFull_US1000124_20240901.txt"));
		List<String> snapshot = Files.readAllLines(axioms_file);
		assertEquals(snapshot.size(), full.size());
		assertEquals(Set.copyOf(snapshot), Set.copyOf(full));
	}

}
