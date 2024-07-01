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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnomedConcreteRoles {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedConcreteRoles.class);

	public static class SnomedConcreteRole {

		public String value;
		public long relationshipGroup;
		public long typeId;

		public SnomedConcreteRole(String value, long relationshipGroup, long typeId) {
			super();
			this.value = value;
			this.relationshipGroup = relationshipGroup;
			this.typeId = typeId;
		}

		@Override
		public String toString() {
			return "[" + relationshipGroup + "] " + typeId + " -> " + value;
		}

	}

	private HashMap<Long, Set<SnomedConcreteRole>> concreteRoles = new HashMap<>();

	public HashMap<Long, Set<SnomedConcreteRole>> getConcreteRoles() {
		return concreteRoles;
	}

	public Set<SnomedConcreteRole> getConcreteRoles(long con) {
		return concreteRoles.get(con);
	}

	public List<SnomedConcreteRole> getUngroupedConcreteRoles(long con) {
		if (getConcreteRoles(con) == null)
			return List.of();
		return getConcreteRoles(con).stream().filter(x -> x.relationshipGroup == 0).toList();
	}

	public List<SnomedConcreteRole> getGroupedConcreteRoles(long con) {
		if (getConcreteRoles(con) == null)
			return List.of();
		return getConcreteRoles(con).stream().filter(x -> x.relationshipGroup != 0).toList();
	}

	public static SnomedConcreteRoles init(Path file) throws IOException {
		SnomedConcreteRoles ret = new SnomedConcreteRoles();
		if (Files.exists(file)) {
			ret.load(file);
		} else {
			LOG.info("No values file: " + file);
		}
		return ret;
	}

	public void load(Path file) throws IOException {
		// id effectiveTime active moduleId sourceId value relationshipGroup
		// typeId characteristicTypeId modifierId
		try (Stream<String> st = Files.lines(file)) {
			st.skip(1).map(line -> line.split("\\t")) //
					.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
					.filter(fields -> Long.parseLong(fields[7]) != SnomedIds.isa) // typeId
					.forEach(fields -> {
						long con = Long.parseLong(fields[4]); // sourceId
						String value = fields[5]; // value
						long relationshipGroup = Long.parseLong(fields[6]); // relationshipGroup
						long typeId = Long.parseLong(fields[7]); // typeId
						concreteRoles.computeIfAbsent(con, x -> new HashSet<>());
						concreteRoles.get(con).add(new SnomedConcreteRole(value, relationshipGroup, typeId));
					});
		}
	}

}
