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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.primitive.LongLists;
import org.eclipse.collections.api.factory.primitive.LongObjectMaps;
import org.eclipse.collections.api.factory.primitive.LongSets;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.set.primitive.ImmutableLongSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;

public class SnomedIsa {

	// Use primitive collections - no boxing!
	private MutableLongObjectMap<MutableLongSet> parentsMap = LongObjectMaps.mutable.empty();
	private MutableLongObjectMap<MutableLongSet> childrenMap = LongObjectMaps.mutable.empty();
	private MutableLongList orderedConcepts = LongLists.mutable.empty();
	
	// Cache for empty set to avoid repeated allocations
	private static final ImmutableLongSet EMPTY_SET = LongSets.immutable.empty();

	public MutableLongObjectMap<MutableLongSet> getParentsMap() {
		return parentsMap;
	}

	public MutableLongObjectMap<MutableLongSet> getChildrenMap() {
		return childrenMap;
	}

	public MutableLongList getOrderedConcepts() {
		return orderedConcepts;
	}

	public static SnomedIsa init(Path file) throws IOException {
		SnomedIsa ret = new SnomedIsa();
		ret.load(file);
		ret.init(SnomedIds.root);
		return ret;
	}

	public static SnomedIsa init(Path file, int version) throws IOException {
		SnomedIsa ret = new SnomedIsa();
		ret.load(file, version);
		ret.init(SnomedIds.root);
		return ret;
	}

	public static SnomedIsa init(MutableLongObjectMap<MutableLongSet> isas) {
		return init(isas, SnomedIds.root);
	}

	public static SnomedIsa init(MutableLongObjectMap<MutableLongSet> isas, long root) {
		SnomedIsa ret = new SnomedIsa();
		ret.parentsMap = isas;
		ret.init(root);
		return ret;
	}

	public void init(long root) {
		initChildren();
		initOrderedConcepts(root);
	}

	private void initChildren() {
		// Primitive forEach - no boxing!
		parentsMap.forEachKeyValue((con, parents) -> {
			parents.forEach(parent -> {
				MutableLongSet children = childrenMap.getIfAbsentPut(parent, LongSets.mutable::empty);
				children.add(con);
			});
		});
	}

	private void initOrderedConcepts(long root) {
		MutableLongSet visited = LongSets.mutable.empty();
		orderedConcepts.add(root);
		visited.add(root);
		initOrderedConcepts(root, visited);
	}

	private void initOrderedConcepts(long con, MutableLongSet visited) {
		// Primitive iteration - no boxing!
		getChildren(con).forEach(sub -> {
			// Check if all parents have been visited
			boolean sups_visited = getParents(sub).allSatisfy(visited::contains);
			if (sups_visited) {
				if (!visited.contains(sub)) {
					orderedConcepts.add(sub);
					visited.add(sub);
				}
				initOrderedConcepts(sub, visited);
			}
		});
	}

	public void load(Path file) throws IOException {
		Stream<String> st = Files.lines(file);
		load(st, 1);
	}

	public void load(Path file, int version) throws IOException {
		Stream<String> st = FullReleaseUtil.getVersion(file, version);
		load(st, 0);
	}

	private void load(Stream<String> st, int skip) {
		st.skip(skip)
			.map(line -> line.split("\\t"))
			.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
			.filter(fields -> Long.parseLong(fields[7]) == SnomedIds.isa) // typeId
			.forEach(fields -> {
				long con = Long.parseLong(fields[4]); // sourceId
				long par = Long.parseLong(fields[5]); // destinationId
				MutableLongSet parents = parentsMap.getIfAbsentPut(con, LongSets.mutable::empty);
				parents.add(par);
			});
	}

	// Return immutable view for safety - prevents accidental modification
	public ImmutableLongSet getParents(long con) {
		MutableLongSet parents = parentsMap.get(con);
		return parents != null ? parents.toImmutable() : EMPTY_SET;
	}

	public boolean hasParent(long con, long parent) {
		MutableLongSet parents = parentsMap.get(con);
		return parents != null && parents.contains(parent);
	}

	public MutableLongSet getAncestors(long con) {
		MutableLongSet visited = LongSets.mutable.empty();
		getAncestors(con, visited);
		return visited;
	}

	private void getAncestors(long con, MutableLongSet visited) {
		getParents(con).forEach(parent -> {
			if (!visited.contains(parent)) {
				visited.add(parent);
				getAncestors(parent, visited);
			}
		});
	}

	public boolean hasAncestor(long con, long ancestor) {
		return hasAncestor(con, ancestor, LongSets.mutable.empty());
	}

	private boolean hasAncestor(long con, long ancestor, MutableLongSet visited) {
		if (hasParent(con, ancestor))
			return true;
		
		// Early exit optimization
		return getParents(con).anySatisfy(parent -> {
			if (visited.contains(parent))
				return false;
			visited.add(parent);
			return hasAncestor(parent, ancestor, visited);
		});
	}

	public ImmutableLongSet getChildren(long con) {
		MutableLongSet children = childrenMap.get(con);
		return children != null ? children.toImmutable() : EMPTY_SET;
	}

	public boolean hasChild(long con, long child) {
		MutableLongSet children = childrenMap.get(con);
		return children != null && children.contains(child);
	}

	public MutableLongSet getDescendants(long con) {
		MutableLongSet visited = LongSets.mutable.empty();
		getDescendants(con, visited);
		return visited;
	}

	private void getDescendants(long con, MutableLongSet visited) {
		getChildren(con).forEach(child -> {
			if (!visited.contains(child)) {
				visited.add(child);
				getDescendants(child, visited);
			}
		});
	}

	public boolean hasDescendant(long con, long descendant) {
		return hasDescendant(con, descendant, LongSets.mutable.empty());
	}

	private boolean hasDescendant(long con, long descendant, MutableLongSet visited) {
		if (hasChild(con, descendant))
			return true;
		
		// Early exit optimization
		return getChildren(con).anySatisfy(child -> {
			if (visited.contains(child))
				return false;
			visited.add(child);
			return hasDescendant(child, descendant, visited);
		});
	}
}
