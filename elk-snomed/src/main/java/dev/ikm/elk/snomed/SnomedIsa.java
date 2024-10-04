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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

public class SnomedIsa {

	private HashMap<Long, Set<Long>> parentsMap = new HashMap<>();

	private HashMap<Long, Set<Long>> childrenMap = new HashMap<>();

	private ArrayList<Long> orderedConcepts = new ArrayList<>();

	public HashMap<Long, Set<Long>> getParentsMap() {
		return parentsMap;
	}

	public HashMap<Long, Set<Long>> getChildrenMap() {
		return childrenMap;
	}

	public ArrayList<Long> getOrderedConcepts() {
		return orderedConcepts;
	}

	private SnomedIsa() {
		super();
	}

	public static SnomedIsa init(Path file) throws IOException {
		SnomedIsa ret = new SnomedIsa();
		ret.load(file);
		ret.init(SnomedIds.root);
		return ret;
	}

	public static SnomedIsa init(HashMap<Long, Set<Long>> isas) {
		return init(isas, SnomedIds.root);
	}

	public static SnomedIsa init(HashMap<Long, Set<Long>> isas, long root) {
		SnomedIsa ret = new SnomedIsa();
		ret.parentsMap = isas;
		ret.init(root);
		return ret;
	}

	private void init(long root) {
		initChildren();
		initOrderedConcepts(root);
	}

	private void initChildren() {
		for (Entry<Long, Set<Long>> es : parentsMap.entrySet()) {
			long con = es.getKey();
			for (long parent : es.getValue()) {
				childrenMap.computeIfAbsent(parent, x -> new HashSet<>());
				childrenMap.get(parent).add(con);
			}
		}
	}

	private void initOrderedConcepts(long root) {
		HashSet<Long> visited = new HashSet<>();
		orderedConcepts.add(root);
		visited.add(root);
		initOrderedConcepts(root, visited);
	}

	private void initOrderedConcepts(long con, HashSet<Long> visited) {
		for (long sub : getChildren(con)) {
			boolean sups_visited = getParents(sub).stream().allMatch(x -> visited.contains(x));
			if (sups_visited) {
				if (!visited.contains(sub)) {
					orderedConcepts.add(sub);
					visited.add(sub);
				}
				initOrderedConcepts(sub, visited);
			}
		}
	}

	public void load(Path file) throws IOException {
		// id effectiveTime active moduleId sourceId destinationId relationshipGroup
		// typeId characteristicTypeId modifierId
		//
		// 116680003 |Is a (attribute)|
		try (Stream<String> st = Files.lines(file)) {
			st.skip(1).map(line -> line.split("\\t")) //
					.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
					.filter(fields -> Long.parseLong(fields[7]) == SnomedIds.isa) // typeId
					.forEach(fields -> {
						long con = Long.parseLong(fields[4]); // sourceId
						long par = Long.parseLong(fields[5]); // destinationId
						parentsMap.computeIfAbsent(con, x -> new HashSet<>());
						parentsMap.get(con).add(par);
					});
		}
	}

	public Set<Long> getParents(long con) {
		return parentsMap.getOrDefault(con, Set.of());
	}

	public boolean hasParent(long con, long parent) {
		return getParents(con).contains(parent);
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
			visited.add(parent);
			if (hasAncestor(parent, ancestor, visited))
				return true;
		}
		return false;
	}

	public Set<Long> getChildren(long con) {
		return childrenMap.getOrDefault(con, Set.of());
	}

	public boolean hasChild(long con, long child) {
		return getChildren(con).contains(child);
	}

	public HashSet<Long> getDescendants(long con) {
		HashSet<Long> visited = new HashSet<>();
		this.getDescendants(con, visited);
		return visited;
	}

	private void getDescendants(long con, HashSet<Long> visited) {
		for (long child : this.getChildren(con)) {
			if (visited.contains(child))
				continue;
			visited.add(child);
			getDescendants(child, visited);
		}
	}

	public boolean hasDescendant(long con, long descendant) {
		return hasDescendant(con, descendant, new HashSet<>());
	}

	private boolean hasDescendant(long con, long descendant, HashSet<Long> visited) {
		if (hasChild(con, descendant))
			return true;
		for (long child : getChildren(con)) {
			if (visited.contains(child))
				continue;
			visited.add(child);
			if (hasDescendant(child, descendant, visited))
				return true;
		}
		return false;
	}

}
