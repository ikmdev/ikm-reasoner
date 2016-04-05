/*
 * #%L
 * elk-reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Oxford University Computing Laboratory
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
/**
 * @author Yevgeny Kazakov, May 15, 2011
 */
package org.semanticweb.elk.reasoner.taxonomy.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericTaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.model.NodeFactory;
import org.semanticweb.elk.reasoner.taxonomy.model.NonBottomTaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNodeFactory;
import org.semanticweb.elk.util.collections.LazySetUnion;

/**
 * A generic implementation of class taxonomy.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the nodes in this taxonomy.
 * @param <N>
 *            The immutable type of nodes in this taxonomy.
 * @param <UN>
 *            The mutable type of nodes in this taxonomy.
 */
public abstract class AbstractUpdateableGenericTaxonomy<
				T extends ElkEntity,
				N extends GenericTaxonomyNode<T, N>,
				UN extends UpdateableTaxonomyNode<T, N, UN>
		>
		extends AbstractDistinctBottomTaxonomy<T, N, UN> {

	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(AbstractUpdateableGenericTaxonomy.class);
	
	/** The factory creating the nodes of this taxonomy. */
	private final NodeFactory<T, UN> nodeFactory_;

	/** The store containing non-bottom nodes in this taxonomy. */
	protected final UpdateableNodeStore<T, UN> nodeStore_;

	/** The canonical member of the top node. */
	protected final T topMember_;
	
	/**
	 * Constructor.
	 * 
	 * @param nodeStore
	 *            Node store for the nodes of this taxonomy.
	 * @param nodeFactory
	 *            Factory that creates nodes of this taxonomy.
	 * @param topMember
	 *            The canonical member of the top node.
	 */
	public AbstractUpdateableGenericTaxonomy(
			final UpdateableNodeStore<T, UN> nodeStore,
			final TaxonomyNodeFactory<T, UN, AbstractDistinctBottomTaxonomy<T, N, UN>> nodeFactory,
			final T topMember) {
		super();
		this.nodeStore_ = nodeStore;
		this.nodeFactory_ = new NodeFactory<T, UN>() {
			@Override
			public UN createNode(final Iterable<? extends T> members,
					final int size) {
				return nodeFactory.createNode(members, size,
						AbstractUpdateableGenericTaxonomy.this);
			}
		};
		this.topMember_ = topMember;
	}

	@Override
	public ComparatorKeyProvider<? super T> getKeyProvider() {
		return nodeStore_.getKeyProvider();
	}

	@Override
	public TaxonomyNode<T> getNode(final T elkClass) {
		TaxonomyNode<T> result = nodeStore_.getNode(elkClass);
		if (result == null && getBottomNode().contains(elkClass)) {
			result = getBottomNode();
		}
		return result;
	}

	@Override
	public UN getNonBottomNode(final T elkEntity) {
		return nodeStore_.getNode(elkEntity);
	}
	
	@Override
	public Set<? extends TaxonomyNode<T>> getNodes() {
		return new LazySetUnion<TaxonomyNode<T>>(nodeStore_.getNodes(),
				Collections.singleton(getBottomNode()));
	}

	@Override
	public Set<? extends UN> getNonBottomNodes() {
		return nodeStore_.getNodes();
	}
	
	@Override
	public UN getTopNode() {
		UN top = nodeStore_.getNode(topMember_);
		if (top == null) {
			top = getCreateNode(Collections.singleton(topMember_));
		}
		return top;
	}

	@Override
	public abstract N getBottomNode();
	
	@Override
	public UN getCreateNode(final Collection<? extends T> members) {
		return nodeStore_.getCreateNode(members, members.size(), nodeFactory_);
	};

	@Override
	public boolean setCreateDirectSupernodes(final NonBottomTaxonomyNode<T> subNode,
			final Iterable<? extends Collection<? extends T>> superMemberSets) {

		final UN node = toInternalNode(subNode);
		
		// TODO: establish consistency by adding default parent to the nodes.
		
		for (final Collection<? extends T> superMembers : superMemberSets) {
			final UN superNode = getCreateNode(superMembers);
			addDirectRelation(superNode, node);
		}

		return node.trySetAllParentsAssigned(true);
	}

	private void addDirectRelation(
			final UN superNode,
			final UN subNode) {
		subNode.addDirectSuperNode(superNode);
		superNode.addDirectSubNode(subNode);
	}

	@Override
	public boolean removeDirectSupernodes(final NonBottomTaxonomyNode<T> subNode) {

		final UN node = toInternalNode(subNode);

		if (!node.trySetAllParentsAssigned(false)) {
			return false;
		}

		final List<UN> superNodes = new ArrayList<UN>();

		// remove all super-class links
		synchronized (node) {
			superNodes.addAll(node.getDirectNonBottomSuperNodes());
			for (final UN superNode : superNodes) {
				node.removeDirectSuperNode(superNode);
			}
		}

		for (final UN superNode : superNodes) {
			synchronized (superNode) {
				superNode.removeDirectSubNode(node);
			}
		}

		return true;
	}

	@Override
	public boolean removeNode(final T member) {
		if (nodeStore_.removeNode(member)) {
			LOGGER_.trace("removed node with member: {}", member);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addToBottomNode(final T member) {
		return unsatisfiableClasses_.put(getKeyProvider().getKey(member),
				member) == null;
	}

	@Override
	public boolean removeFromBottomNode(final T member) {
		return unsatisfiableClasses_
				.remove(getKeyProvider().getKey(member)) != null;
	}

	@SuppressWarnings("unchecked")
	protected UN toInternalNode(final NonBottomTaxonomyNode<T> node) {
		if (node.getTaxonomy() != this) {
			throw new IllegalArgumentException(
					"The sub-node must belong to this taxonomy: " + node);
		}
		// By construction, if the node is in this taxonomy, it is of type N.
		try {
			return (UN) node;
		} catch (final ClassCastException e) {
			throw new IllegalArgumentException(
					"The sub-node must belong to this taxonomy: " + node);
		}
	}
	
}