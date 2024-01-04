
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * A node of instances in an InstanceTaxonomy. It can be associated with
 * multiple type nodes.
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            The type of members of the related type nodes.
 * @param <I>
 *            The type of members of this node.
 */
public interface InstanceNode<T extends ElkEntity, I extends ElkEntity>
		extends Node<I>, HasTaxonomy<T> {

	/**
	 * Get an unmodifiable set of {@link TypeNode}s of which the members of this
	 * {@link InstanceNode} are direct instances. A member is a direct instance
	 * of a {@link TypeNode} if it is an instance of the {@link TypeNode} and
	 * not an instance of any sub-node of a {@link TypeNode}.
	 * 
	 * @return nodes for direct types of this node's members
	 */
	public Set<? extends TypeNode<T, I>> getDirectTypeNodes();

	/**
	 * Get an unmodifiable set of {@link TypeNode}s of which the members of this
	 * {@link InstanceNode} are (possibly indirect) instances.
	 * 
	 * @return list of nodes for instances of this node's members
	 */
	public Set<? extends TypeNode<T, I>> getAllTypeNodes();

}
