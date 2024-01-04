
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * A node in a taxonomy that has instances. This mainly makes sense for classes
 * and individuals, and for properties and pairs of individuals.
 * 
 * @author Markus Kroetzsch
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of objects in this node
 * @param <I>
 *            the type of instances of this node
 */
public interface TypeNode<T extends ElkEntity, I extends ElkEntity> extends
		TaxonomyNode<T> {

	/**
	 * Get an unmodifiable set of {@link InstanceNode}s that are direct
	 * instances of this {@link TypeNode}. An {@link InstanceNode} is a direct
	 * instance of a {@link TypeNode} if it is an instance of the
	 * {@link TypeNode} and not an instance of any sub-node of a
	 * {@link TypeNode}.
	 * 
	 * @return nodes for direct instances of this node's members
	 */
	public Set<? extends InstanceNode<T, I>> getDirectInstanceNodes();

	/**
	 * Get an unmodifiable set of {@link InstanceNode}s that are (possibly
	 * indirect) instances of this {@link TypeNode}.
	 * 
	 * @return nodes for all instances of this node's members
	 */
	public Set<? extends InstanceNode<T, I>> getAllInstanceNodes();

	@Override
	public Set<? extends TypeNode<T, I>> getDirectSuperNodes();

	@Override
	public Set<? extends TypeNode<T, I>> getAllSuperNodes();

	@Override
	public Set<? extends TypeNode<T, I>> getDirectSubNodes();

	@Override
	public Set<? extends TypeNode<T, I>> getAllSubNodes();

}
