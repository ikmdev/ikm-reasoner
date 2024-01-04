
package org.semanticweb.elk.reasoner.taxonomy.model;

/**
 * Instantiates {@link Node}s containing the supplied members.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the created nodes.
 * @param <N>
 *            The type of the created nodes.
 */
public interface NodeFactory<T, N extends Node<T>> {

	/**
	 * Instantiates {@link Node} containing the supplied members.
	 * 
	 * @param members
	 *            The members the node should contain.
	 * @param size
	 *            The number of the members the node should contain.
	 * @return The new {@link Node} containing the supplied members.
	 */
	N createNode(Iterable<? extends T> members, int size);

}
