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

public class SnomedRoles {

	public static long root = SnomedIds.root;

	public static long isa = SnomedIds.isa;

	public static class SnomedRole {

		public long destinationId;
		public long relationshipGroup;
		public long typeId;

		public SnomedRole(long destinationId, long relationshipGroup, long typeId) {
			super();
			this.destinationId = destinationId;
			this.relationshipGroup = relationshipGroup;
			this.typeId = typeId;
		}

		@Override
		public String toString() {
			return "[" + relationshipGroup + "] " + typeId + " -> " + destinationId;
		}

	}

	private HashMap<Long, Set<SnomedRole>> roles = new HashMap<>();

	public HashMap<Long, Set<SnomedRole>> getRoles() {
		return roles;
	}

	public Set<SnomedRole> getRoles(long con) {
		return roles.get(con);
	}

	public List<SnomedRole> getUngroupedRoles(long con) {
		if (roles.get(con) == null)
			return List.of();
		return roles.get(con).stream().filter(x -> x.relationshipGroup == 0).toList();
	}

	public List<SnomedRole> getGroupedRoles(long con) {
		if (roles.get(con) == null)
			return List.of();
		return roles.get(con).stream().filter(x -> x.relationshipGroup != 0).toList();
	}

	public static SnomedRoles init(Path file) throws IOException {
		SnomedRoles ret = new SnomedRoles();
		ret.load(file);
		return ret;
	}

	public void load(Path file) throws IOException {
		// id effectiveTime active moduleId sourceId destinationId relationshipGroup
		// typeId characteristicTypeId modifierId
		try (Stream<String> st = Files.lines(file)) {
			st.skip(1).map(line -> line.split("\\t")) //
					.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
					.filter(fields -> Long.parseLong(fields[7]) != isa) // typeId
					.forEach(fields -> {
						long con = Long.parseLong(fields[4]); // sourceId
						long destination = Long.parseLong(fields[5]); // destinationId
						long relationshipGroup = Long.parseLong(fields[6]); // relationshipGroup
						long typeId = Long.parseLong(fields[7]); // typeId
						roles.computeIfAbsent(con, _ -> new HashSet<>());
						roles.get(con).add(new SnomedRole(destination, relationshipGroup, typeId));
					});
		}
	}

}
