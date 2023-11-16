package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration Testing with SNOMED
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
import java.util.Set;

public class SnomedIsa {

	public static long root = SnomedOwlOntology.root;

	private HashMap<Long, Set<Long>> isas = new HashMap<>();

	public static SnomedIsa init(Path file) throws IOException {
		SnomedIsa ret = new SnomedIsa();
		ret.load(file);
		return ret;
	}

	public static SnomedIsa init(HashMap<Long, Set<Long>> isas) {
		SnomedIsa ret = new SnomedIsa();
		ret.isas = isas;
		return ret;
	}

	public void load(Path file) throws IOException {
		// id effectiveTime active moduleId sourceId destinationId relationshipGroup
		// typeId characteristicTypeId modifierId
		//
		// 116680003 |Is a (attribute)|
		Files.lines(file).skip(1).map(line -> line.split("\\t")) //
				.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
				.filter(fields -> Long.parseLong(fields[7]) == 116680003) // typeId
				.forEach(fields -> {
					long con = Long.parseLong(fields[4]); // sourceId
					long par = Long.parseLong(fields[5]); // destinationId
					isas.computeIfAbsent(con, x -> new HashSet<>());
					isas.get(con).add(par);
				});
	}

	public HashMap<Long, Set<Long>> getIsas() {
		return isas;
	}

	public Set<Long> getParents(long con) {
		return isas.getOrDefault(con, Set.of());
	}

	public boolean hasParent(long con, long ancestor) {
		return getParents(con).contains(ancestor);
	}

	public HashSet<Long> getAncestors(long con) {
		HashSet<Long> visited = new HashSet<>();
		this.getAncestors(con, visited);
		return visited;
	}

	private void getAncestors(long con, HashSet<Long> visited) {
		for (long parent : this.getParents(con)) {
			if (visited.contains(parent))
				continue;
			visited.add(parent);
			getAncestors(parent, visited);
		}
	}

	public boolean hasAncestor(long con, long ancestor) {
		return hasAncestor(con, ancestor, new HashSet<>());
	}

	private boolean hasAncestor(long con, long ancestor, HashSet<Long> visited) {
		if (hasParent(con, ancestor))
			return true;
		for (long parent : getParents(con)) {
			if (visited.contains(parent))
				continue;
			if (hasAncestor(parent, ancestor, visited))
				return true;
			visited.add(parent);
		}
		return false;
	}

}
