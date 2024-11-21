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

public class SnomedDescriptions {

	public enum DescriptionType {

		FULLY_SPECIFIED(900000000000003001l, "Fully specified name"),

		// PREFERRED(1, "Preferred"),

		SYNONYM(900000000000013009l, "Synonym"),

		DEFINITION(900000000000550004l, "Definition");

		public long id;

		public String name;

		private DescriptionType(long id, String name) {
			this.id = id;
			this.name = name;
		}

		public static DescriptionType getDescriptionType(long type) {
			for (DescriptionType t : values()) {
				if (t.id == type)
					return t;
			}
			return null;
		}

		public static DescriptionType getDescriptionType(String type) {
			for (DescriptionType t : values()) {
				if (t.name.equals(type))
					return t;
			}
			return null;
		}

	}

	private HashMap<Long, String> fsns = new HashMap<>();

	public String getFsn(long con) {
		return fsns.get(con);
	}

	public static SnomedDescriptions init(Path file) throws IOException {
		SnomedDescriptions ret = new SnomedDescriptions();
		ret.load(file);
		return ret;
	}

	public void load(Path file) throws IOException {
		// id effectiveTime active moduleId conceptId languageCode typeId term
		// caseSignificanceId
		try (Stream<String> st = Files.lines(file)) {
			st.skip(1).map(line -> line.split("\\t")) //
					.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
					.filter(fields -> Long.parseLong(fields[6]) == DescriptionType.FULLY_SPECIFIED.id) // typeId
					.forEach(fields -> {
						long con = Long.parseLong(fields[4]); // conceptId
						String fsn = fields[7]; // term
//						if (fsns.get(con) != null)
//							throw new RuntimeException("" + fields);
						fsns.put(con, fsn);
					});
		}
	}

	public static String getVersion(Path file) throws IOException {
		// id effectiveTime active moduleId conceptId languageCode typeId term
		// caseSignificanceId
		try (Stream<String> st = Files.lines(file)) {
			 return st.skip(1).map(line -> line.split("\\t")) //
					.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
					.filter(fields -> Long.parseLong(fields[4]) == SnomedIds.root) // conceptId
					.map(fields -> fields[7]) // term
					.filter(term -> term.startsWith("SNOMED Clinical Terms version:")) //
					.findFirst().get();
		}
	}

}
