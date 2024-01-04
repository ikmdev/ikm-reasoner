
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * A taxonomy node that is not a bottom node.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of this node.
 */
public interface NonBottomTaxonomyNode<T extends ElkEntity>
		extends TaxonomyNode<T> {

	/**
	 * Returns immutable set of direct super-nodes.
	 * 
	 * @return immutable set of direct super-nodes.
	 * @see TaxonomyNode#getDirectSuperNodes()
	 */
	Set<? extends NonBottomTaxonomyNode<T>> getDirectNonBottomSuperNodes();

	/**
	 * Returns immutable set of direct sub-nodes that does <strong>not</strong>
	 * contain the bottom node.
	 * 
	 * @return immutable set of direct sub-nodes that does <strong>not</strong>
	 * contain the bottom node.
	 */
	Set<? extends NonBottomTaxonomyNode<T>> getDirectNonBottomSubNodes();

}
