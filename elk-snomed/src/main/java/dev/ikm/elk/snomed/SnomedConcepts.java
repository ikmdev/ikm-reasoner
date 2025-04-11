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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.DefinitionType;

public class SnomedConcepts {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(SnomedConcepts.class);

	public enum DefinitionStatus {

		// 900000000000074008 |Not sufficiently defined by necessary conditions
		// definition status (core metadata concept)|
		NECESSARY(900000000000074008l, "Necessary"),

		// 900000000000073002 |Sufficiently defined by necessary conditions definition
		// status (core metadata concept)|
		SUFFICIENT(900000000000073002l, "Sufficient");

		public long id;

		public String name;

		private DefinitionStatus(long id, String name) {
			this.id = id;
			this.name = name;
		}

		public static DefinitionStatus getDefinitionStatus(long status) {
			for (DefinitionStatus ds : values()) {
				if (ds.id == status)
					return ds;
			}
			return null;
		}

		public static DefinitionStatus getDefinitionStatus(String status) {
			for (DefinitionStatus ds : values()) {
				if (ds.name.equals(status))
					return ds;
			}
			return null;
		}

		public DefinitionType getDefinitionType() {
			return switch (this) {
			case NECESSARY -> DefinitionType.SubConcept;
			case SUFFICIENT -> DefinitionType.EquivalentConcept;
			};
		}

	}

	private HashMap<Long, DefinitionStatus> definitionStatus = new HashMap<>();

	public DefinitionStatus getDefinitionStatus(long con) {
		return definitionStatus.get(con);
	}

	public static SnomedConcepts init(Path file) throws IOException {
		SnomedConcepts ret = new SnomedConcepts();
		ret.load(file);
		return ret;
	}

	public void load(Path file) throws IOException {
		// id effectiveTime active moduleId definitionStatusId
		try (Stream<String> st = Files.lines(file)) {
			st.skip(1).map(line -> line.split("\\t")) //
					.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
					.forEach(fields -> {
						long con = Long.parseLong(fields[0]); // conceptId
						DefinitionStatus status = DefinitionStatus.getDefinitionStatus(Long.parseLong(fields[4])); // definitionStatusId
						definitionStatus.put(con, status);
					});
		}
	}

}
