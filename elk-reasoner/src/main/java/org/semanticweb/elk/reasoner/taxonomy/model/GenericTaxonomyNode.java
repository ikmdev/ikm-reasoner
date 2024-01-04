
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Taxonomy node with parameterized type of nodes with which it may be
 * associated.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of this node.
 * @param <N>
 *            The type of nodes with which this node can be associated.
 */
public interface GenericTaxonomyNode<
				T extends ElkEntity,
				N extends GenericTaxonomyNode<T, N>
		>
		extends TaxonomyNode<T> {

	@Override
	public Set<? extends N> getDirectSuperNodes();

	@Override
	public Set<? extends N> getAllSuperNodes();

	@Override
	public Set<? extends N> getDirectSubNodes();

	@Override
	public Set<? extends N> getAllSubNodes();

	public static interface Projection<T extends ElkEntity>
			extends GenericTaxonomyNode<T, Projection<T>> {
		// Empty.
	}

}
