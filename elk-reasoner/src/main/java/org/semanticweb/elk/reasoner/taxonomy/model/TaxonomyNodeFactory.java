
package org.semanticweb.elk.reasoner.taxonomy.model;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Instantiates {@link Node}s based on the supplied members and an additional
 * parameter.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the created nodes.
 * @param <N>
 *            The type of the created nodes.
 * @param <P>
 *            The type of the additional parameter.
 */
public interface TaxonomyNodeFactory<
				T extends ElkEntity,
				N extends Node<T>,
				P
		> {

	/**
	 * Instantiates {@link Node} based on the supplied members and an additional
	 * parameter.
	 * 
	 * @param members
	 *            The members the node should contain.
	 * @param size
	 *            The number of the members the node should contain.
	 * @param param
	 *            The additional parameter for the node creation.
	 * @return The new {@link Node}
	 */
	N createNode(Iterable<? extends T> members, int size, P param);
	
}
