package org.semanticweb.elk.reasoner.taxonomy;

/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2012 Department of Computer Science, University of Oxford
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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.printers.OwlFunctionalStylePrinter;
import org.semanticweb.elk.reasoner.taxonomy.DepthFirstSearch.Direction;
import org.semanticweb.elk.reasoner.taxonomy.impl.AbstractTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.NodeStore;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNode;
import org.semanticweb.elk.util.collections.Condition;
import org.semanticweb.elk.util.collections.Operations;

/**
 * A {@link Taxonomy} built by hand from an expected test output, or directly by
 * a test. It holds the members of the taxonomy and nothing else: no instances.
 *
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 * @param <T>
 *            the type of the members of the nodes
 */
public class MockTaxonomy<T extends ElkEntity> extends AbstractTaxonomy<T> {

	protected final ExtremeNode<T> top;
	protected MockBottomNode bottom;
	protected final Map<TaxonomyNode<T>, Set<TaxonomyNode<T>>> parentMap = new HashMap<TaxonomyNode<T>, Set<TaxonomyNode<T>>>();
	protected final Map<TaxonomyNode<T>, Set<TaxonomyNode<T>>> childrenMap = new HashMap<TaxonomyNode<T>, Set<TaxonomyNode<T>>>();
	protected final Map<Object, TaxonomyNode<T>> nodeIndex = new HashMap<Object, TaxonomyNode<T>>();

	private int nodesWithoutParent = 0;
	private int nodesWithoutChildren = 0;

	/** provides keys that are used for hashing instead of the members */
	private final ComparatorKeyProvider<ElkEntity> keyProvider_;

	MockTaxonomy(T top, T bottom,
			final ComparatorKeyProvider<ElkEntity> keyProvider) {
		this.keyProvider_ = keyProvider;
		this.top = new MockTopNode(top);
		this.bottom = new MockBottomNode(bottom);

		initNode(this.top);
		initNode(this.bottom);
	}

	private void initNode(TaxonomyNode<T> node) {
		for (T member : node) {
			nodeIndex.put(keyProvider_.getKey(member), node);
		}

		parentMap.put(node, new HashSet<TaxonomyNode<T>>());
		childrenMap.put(node, new HashSet<TaxonomyNode<T>>());
	}

	@Override
	public ComparatorKeyProvider<ElkEntity> getKeyProvider() {
		return keyProvider_;
	}

	@Override
	public MockTaxonomyNode getNode(T elkObject) {
		return (MockTaxonomyNode) nodeIndex.get(keyProvider_.getKey(elkObject));
	}

	@Override
	public Set<? extends TaxonomyNode<T>> getNodes() {
		return parentMap.keySet();
	}

	@Override
	public ExtremeNode<T> getTopNode() {
		return top;
	}

	@Override
	public ExtremeNode<T> getBottomNode() {
		return bottom != null ? bottom : top;
	}

	protected void makeInconsistent() {
		parentMap.clear();
		childrenMap.clear();
		nodeIndex.clear();
		top.addMembers(bottom);
		bottom = null;
		// init the only node in the taxonomy
		initNode(top);
	}

	public boolean isConsistent() {
		return top != bottom;
	}

	@SuppressWarnings("unchecked")
	public MutableTaxonomyNode<T> getCreateNode(Collection<T> members) {
		// check for inconsistency
		if (bottom != null && members.contains(top.getCanonicalMember())
				&& members.contains(bottom.getCanonicalMember())) {
			makeInconsistent();
		}
		if (bottom == null) {
			// inconsistent
			top.addMembers(members);
			return top;
		}
		// else nodes for some members may already exist
		MutableTaxonomyNode<T> node = null;
		ExtremeNode<T> extreme = null;

		for (T member : members) {
			if (member.equals(top.getCanonicalMember())
					|| member.equals(bottom.getCanonicalMember())) {
				// this is an unchecked cast. could be avoided at the expense of
				// another LoC but i'm gonna punt on that
				extreme = (ExtremeNode<T>) getNode(member);
			} else {
				MockTaxonomyNode existing = getNode(member);
				// raise an error since this class doesn't support node merging
				assert node == null || node == existing;

				node = existing;
			}
		}

		if (node == null) {
			if (extreme != null) {
				extreme.addMembers(members);
				node = extreme;
			} else {
				node = new MockTaxonomyNode(members);
				initNode(node);
				nodesWithoutParent++;
				nodesWithoutChildren++;
			}
		} else {
			if (extreme != null) {
				extreme.merge(node);
			} else {
				node.addMembers(members);
			}
		}

		return node;
	}

	protected void remove(TaxonomyNode<T> node) {
		// clean-up all indices
		for (TaxonomyNode<T> subNode : node.getDirectSubNodes()) {
			parentMap.get(subNode).remove(node);
		}

		for (TaxonomyNode<T> supNode : node.getDirectSuperNodes()) {
			childrenMap.get(supNode).remove(node);
		}
		// update counters
		if (parentMap.get(node).isEmpty()) {
			nodesWithoutParent--;
		}

		if (childrenMap.get(node).isEmpty()) {
			nodesWithoutChildren--;
		}
		// the final cleanup
		parentMap.remove(node);
		childrenMap.remove(node);
	}

	/**
	 *
	 * @author Pavel Klinov
	 *
	 *         pavel.klinov@uni-ulm.de
	 */
	interface MutableTaxonomyNode<T extends ElkEntity> extends TaxonomyNode<T> {

		void addDirectParent(TaxonomyNode<T> parent);

		void addMembers(Iterable<T> members);
	}

	/**
	 *
	 * @author Pavel Klinov
	 *
	 *         pavel.klinov@uni-ulm.de
	 */
	protected class MockTaxonomyNode implements MutableTaxonomyNode<T> {

		final SortedSet<T> members;

		MockTaxonomyNode(Collection<T> members) {
			this.members = new TreeSet<T>(keyProvider_.getComparator());
			this.members.addAll(members);
		}

		@Override
		public Taxonomy<T> getTaxonomy() {
			return MockTaxonomy.this;
		}

		@Override
		public ComparatorKeyProvider<ElkEntity> getKeyProvider() {
			return keyProvider_;
		}

		@Override
		public Iterator<T> iterator() {
			return members.iterator();
		}

		@Override
		public boolean contains(T member) {
			return members.contains(member);
		}

		@Override
		public int size() {
			return members.size();
		}

		@Override
		public T getCanonicalMember() {
			return members.isEmpty() ? null : members.iterator().next();
		}

		@Override
		public Set<TaxonomyNode<T>> getDirectSuperNodes() {
			Set<TaxonomyNode<T>> sup = MockTaxonomy.this.parentMap.get(this);

			return sup.isEmpty()
					? Collections.<TaxonomyNode<T>> singleton(getTopNode())
					: Collections.unmodifiableSet(sup);
		}

		@Override
		public Set<TaxonomyNode<T>> getAllSuperNodes() {
			Set<TaxonomyNode<T>> sups = new HashSet<TaxonomyNode<T>>();
			computeSuccessors(this, sups, Direction.UP);
			return sups;
		}

		@Override
		public Set<TaxonomyNode<T>> getDirectSubNodes() {
			Set<TaxonomyNode<T>> sub = MockTaxonomy.this.childrenMap.get(this);

			return sub.isEmpty()
					? Collections.<TaxonomyNode<T>> singleton(getBottomNode())
					: Collections.unmodifiableSet(sub);
		}

		@Override
		public Set<TaxonomyNode<T>> getAllSubNodes() {
			Set<TaxonomyNode<T>> subs = new HashSet<TaxonomyNode<T>>();

			computeSuccessors(this, subs, Direction.DOWN);

			if (subs.size() > 2) {
				subs.remove(getTopNode());
				subs.remove(getBottomNode());
			}

			return subs;
		}

		@Override
		public void addDirectParent(TaxonomyNode<T> parent) {
			// assert parent.getTaxonomy() == getTaxonomy();

			if (this != getBottomNode() && parent != getTopNode()) {
				if (parent == getBottomNode()) {
					getBottomNode().merge(this);
				} else {
					if (childrenMap.get(parent).isEmpty()) {
						nodesWithoutChildren--;
					}

					if (parentMap.get(this).isEmpty()) {
						nodesWithoutParent--;
					}

					parentMap.get(this).add(parent);
					childrenMap.get(parent).add(this);
				}
			}
		}

		private void computeSuccessors(TaxonomyNode<T> node,
				Set<TaxonomyNode<T>> successors, Direction dir) {

			for (TaxonomyNode<T> succ : dir == Direction.UP
					? node.getDirectSuperNodes()
					: node.getDirectSubNodes()) {
				successors.add(succ);
				computeSuccessors(succ, successors, dir);
			}
		}

		@Override
		public void addMembers(Iterable<T> newMembers) {
			for (T newMember : newMembers) {
				this.members.add(newMember);
				MockTaxonomy.this.nodeIndex.put(keyProvider_.getKey(newMember),
						this);
			}
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();

			for (T member : members) {
				builder.append(
						OwlFunctionalStylePrinter.toString(member) + ",");
			}

			return builder.toString();
		}
	}

	/**
	 * Only Top and Bot implement this since they're the only ones supporting
	 * the merge op so far
	 *
	 * @author Pavel Klinov
	 *
	 *         pavel.klinov@uni-ulm.de
	 */
	interface ExtremeNode<T extends ElkEntity> extends MutableTaxonomyNode<T> {
		void merge(TaxonomyNode<T> node);
	}

	/**
	 *
	 * @author Pavel Klinov
	 *
	 *         pavel.klinov@uni-ulm.de
	 */
	class MockTopNode extends MockTaxonomyNode implements ExtremeNode<T> {

		MockTopNode(T top) {
			super(Collections.singleton(top));
		}

		@Override
		public void merge(TaxonomyNode<T> node) {
			// Merge that node into top
			// It's possible to have a generic "merge" method but it's
			// non-trivial
			addMembers(node);
			remove(node);
		}

		@Override
		public void addDirectParent(TaxonomyNode<T> node) {
			if (node == getBottomNode()) {
				// this is the special case of an unsatisfiable taxonomy
				makeInconsistent();
			} else {
				merge(node);
			}
		}

		@Override
		public Set<TaxonomyNode<T>> getDirectSuperNodes() {
			return Collections.emptySet();
		}

		@Override
		public Set<TaxonomyNode<T>> getAllSuperNodes() {
			return Collections.emptySet();
		}

		@Override
		public Set<TaxonomyNode<T>> getDirectSubNodes() {
			// all nodes that have no non-top parent
			final boolean empty = MockTaxonomy.this.childrenMap.size() == 2;

			return Operations.filter(MockTaxonomy.this.parentMap.keySet(),
					new Condition<TaxonomyNode<T>>() {
						@Override
						public boolean holds(TaxonomyNode<T> node) {
							return node != getTopNode() // it's not Top
									&& MockTaxonomy.this.parentMap.get(node)
											.isEmpty() // no direct parents
									&& (node != getBottomNode() || empty); // bottom
																			// but
																			// there're
																			// no
																			// intermediate
																			// nodes
						}
					}, empty ? 1 : nodesWithoutParent);
		}

		@Override
		public Set<TaxonomyNode<T>> getAllSubNodes() {
			// all nodes
			return Operations.filter(MockTaxonomy.this.parentMap.keySet(),
					new Condition<TaxonomyNode<T>>() {
						@Override
						public boolean holds(TaxonomyNode<T> node) {
							return node != getTopNode();
						}
					}, MockTaxonomy.this.parentMap.keySet().size() - 1);
		}
	}

	/**
	 *
	 * @author Pavel Klinov
	 *
	 *         pavel.klinov@uni-ulm.de
	 */
	class MockBottomNode extends MockTaxonomyNode implements ExtremeNode<T> {

		MockBottomNode(T bot) {
			super(Collections.singleton(bot));
		}

		@Override
		public void merge(TaxonomyNode<T> node) {
			addMembers(node);
			remove(node);
		}

		@Override
		public Set<TaxonomyNode<T>> getDirectSuperNodes() {
			// all nodes that have no non-bot children
			final boolean empty = MockTaxonomy.this.childrenMap.size() == 2;

			return Operations.filter(MockTaxonomy.this.childrenMap.keySet(),
					new Condition<TaxonomyNode<T>>() {
						@Override
						public boolean holds(TaxonomyNode<T> node) {
							return node != getBottomNode() && // not Bot
									MockTaxonomy.this.childrenMap.get(node)
											.isEmpty() // no children
									&& (node != getTopNode() || empty); // Top
																		// but
																		// there're
																		// no
																		// intermediate
																		// nodes;
						}
					}, empty ? 1 : nodesWithoutChildren);
		}

		@Override
		public Set<TaxonomyNode<T>> getAllSuperNodes() {
			return Operations.filter(MockTaxonomy.this.childrenMap.keySet(),
					new Condition<TaxonomyNode<T>>() {
						@Override
						public boolean holds(TaxonomyNode<T> node) {
							return node != getBottomNode();
						}
					}, MockTaxonomy.this.childrenMap.size() - 1);
		}

		@Override
		public Set<TaxonomyNode<T>> getDirectSubNodes() {
			return Collections.emptySet();
		}

		@Override
		public Set<TaxonomyNode<T>> getAllSubNodes() {
			return Collections.emptySet();
		}
	}

	@Override
	public boolean addListener(final Taxonomy.Listener<T> listener) {
		// Ignore
		return false;
	}

	@Override
	public boolean removeListener(final Taxonomy.Listener<T> listener) {
		// Ignore
		return false;
	}

	@Override
	public boolean addListener(final NodeStore.Listener<T> listener) {
		// Ignore
		return false;
	}

	@Override
	public boolean removeListener(final NodeStore.Listener<T> listener) {
		// Ignore
		return false;
	}

}
