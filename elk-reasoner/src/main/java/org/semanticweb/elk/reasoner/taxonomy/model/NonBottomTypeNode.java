
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * A type node that is not a bottom node.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of this node.
 * @param <I>
 *            The type of members of the related instance nodes.
 */
public interface NonBottomTypeNode<T extends ElkEntity, I extends ElkEntity>
		extends TypeNode<T, I>, NonBottomTaxonomyNode<T> {

	@Override
	Set<? extends NonBottomTypeNode<T, I>> getDirectNonBottomSuperNodes();

	@Override
	Set<? extends NonBottomTypeNode<T, I>> getDirectNonBottomSubNodes();
	
}
