
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * A node in a taxonomy. It is a {@link Node} that refers to its sub and super
 * nodes in a {@link Taxonomy}.
 * 
 * @author Markus Kroetzsch
 * @param <T>
 *            the type of objects stored in the nodes
 */
public interface TaxonomyNode<T extends ElkEntity>
		extends Node<T>, HasTaxonomy<T> {

	/**
	 * Get an unmodifiable set of nodes for ElkObjects that are direct
	 * super-objects of this Node.
	 * 
	 * @return set of nodes for direct super-objects of this node's members
	 */
	public Set<? extends TaxonomyNode<T>> getDirectSuperNodes();

	/**
	 * Computes an unmodifiable set of nodes for ElkObjects that are (possibly
	 * indirect) super-objects of members of this Node. This is the smallest set
	 * of nodes that contains all direct super-nodes of this node, and all
	 * direct super-nodes of every node in this set.
	 * 
	 * @return set of nodes for sub-objects of this node's members
	 */
	public Set<? extends TaxonomyNode<T>> getAllSuperNodes();

	/**
	 * Get an unmodifiable set of nodes for ElkObjects that are direct
	 * sub-objects of this Node.
	 * 
	 * @return set of nodes for direct sub-objects of this node's members
	 */
	public Set<? extends TaxonomyNode<T>> getDirectSubNodes();

	/**
	 * Computes an unmodifiable set of nodes for ElkObjects that are (possibly
	 * indirect) sub-objects of members of this Node. This is the smallest set
	 * of nodes that contains all direct sub-nodes of this node, and all direct
	 * sub-nodes of every node in this set.
	 * 
	 * @return set of nodes for sub-objects of this node's members
	 */
	public Set<? extends TaxonomyNode<T>> getAllSubNodes();

}
