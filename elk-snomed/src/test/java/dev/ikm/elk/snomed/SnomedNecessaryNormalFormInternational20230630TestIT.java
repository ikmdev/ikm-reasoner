package dev.ikm.elk.snomed;

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

public class SnomedNecessaryNormalFormInternational20230630TestIT extends SnomedNecessaryNormalFormTestBase
		implements SnomedVersionInternational {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory
			.getLogger(SnomedNecessaryNormalFormInternational20230630TestIT.class);

	@Override
	public String getVersion() {
		return "20230630";
	}

	{
		expected_concept_cnt = 362074;
	}

}