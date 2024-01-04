
package org.semanticweb.elk.reasoner.taxonomy.impl;

import org.semanticweb.elk.reasoner.taxonomy.model.NodeFactory;
import org.semanticweb.elk.reasoner.taxonomy.model.NodeStore;

/**
 * Generic node store that can be modified.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the nodes in this store.
 * @param <N>
 *            The type of nodes in this store.
 */
public interface UpdateableNodeStore<T, N extends UpdateableNode<T>>
		extends NodeStore<T, N> {

	/**
	 * Returns the node that contains the members provided in arguments. If such
	 * a node is not in this store, it is created using the provided factory and
	 * inserted into this store.
	 * 
	 * @param members
	 *            The members of the returned node.
	 * @param size
	 *            The number of the members.
	 * @param factory
	 *            The factory creating nodes that can be stored in this store.
	 * @return The node containing the provided members.
	 */
	N getCreateNode(Iterable<? extends T> members, int size,
			NodeFactory<T, N> factory);

	/**
	 * Removes the node containing the specified member from the taxonomy.
	 * 
	 * @param member
	 *            The member whose node should be removed.
	 * @return <code>true</code> if and only if some node was removed.
	 */
	boolean removeNode(T member);

}
