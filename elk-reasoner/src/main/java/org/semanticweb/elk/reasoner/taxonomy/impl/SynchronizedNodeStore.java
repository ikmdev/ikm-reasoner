
package org.semanticweb.elk.reasoner.taxonomy.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.NodeFactory;
import org.semanticweb.elk.util.collections.ArrayHashMap;
import org.semanticweb.elk.util.collections.ArrayHashSet;

/**
 * An updateable generic node store whose methods are synchronized.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the nodes in this store.
 * @param <N>
 *            The type of nodes in this store.
 */
public class SynchronizedNodeStore<T, N extends UpdateableNode<T>>
		extends AbstractNodeStore<T, N> implements UpdateableNodeStore<T, N> {

	/**
	 * The key provider for members of the nodes in this node store.
	 */
	private final ComparatorKeyProvider<? super T> keyProvider_;
	/**
	 * The map from the member keys to the nodes containing the members.
	 */
	private final Map<Object, N> nodeLookup_;
	/**
	 * The set of all nodes.
	 */
	private final Set<N> allNodes_;

	/**
	 * Creates the node store with the provided initial capacity.
	 * 
	 * @param capacity
	 *            The initial capacity.
	 * @param keyProvider
	 *            The key provider for members of the nodes in this node store.
	 */
	public SynchronizedNodeStore(final int capacity,
			final ComparatorKeyProvider<? super T> keyProvider) {
		keyProvider_ = keyProvider;
		nodeLookup_ = new ArrayHashMap<Object, N>(capacity);
		allNodes_ = new ArrayHashSet<N>(capacity);
	}

	/**
	 * Creates the node store.
	 * 
	 * @param keyProvider
	 *            The key provider for members of the nodes in this node store.
	 */
	public SynchronizedNodeStore(
			final ComparatorKeyProvider<? super T> keyProvider) {
		this(127, keyProvider);
	}

	@Override
	public synchronized N getNode(final T member) {
		return nodeLookup_.get(keyProvider_.getKey(member));
	}

	@Override
	public synchronized Set<N> getNodes() {
		return Collections.unmodifiableSet(allNodes_);
	}

	@Override
	public ComparatorKeyProvider<? super T> getKeyProvider() {
		return keyProvider_;
	}

	@Override
	public synchronized N getCreateNode(final Iterable<? extends T> members,
			final int size, final NodeFactory<T, N> factory) {
		for (final T member : members) {
			final N previous = getNode(member);
			if (previous != null) {
				// TODO: This should fire an exception instead of this.
				synchronized (previous) {
					if (previous.size() < size) {
						previous.setMembers(members);
					} else {
						return previous;
					}
				}
				for (final T m : members) {
					nodeLookup_.put(keyProvider_.getKey(m), previous);
				}
				fireMemberForNodeAppeared(previous);
				return previous;
			}
		}
		final N node = factory.createNode(members, size);
		final T canonicalMember = node.getCanonicalMember();
		final N previous = nodeLookup_.put(keyProvider_.getKey(canonicalMember),
				node);
		if (previous != null) {
			return previous;
		}
		allNodes_.add(node);
		for (final T member : node) {
			if (member != canonicalMember) {
				nodeLookup_.put(keyProvider_.getKey(member), node);
			}
		}
		fireMemberForNodeAppeared(node);
		return node;
	}

	@Override
	public synchronized boolean removeNode(final T member) {

		final N node = getNode(member);
		if (node == null) {
			return false;
		}

		boolean changed = false;
		if (allNodes_.remove(node)) {
			for (final T m : node) {
				changed |= nodeLookup_.remove(keyProvider_.getKey(m)) != null;
			}
			fireMemberForNodeDisappeared(node);
		}

		return changed;
	}

}
