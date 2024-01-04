
package org.semanticweb.elk.reasoner.taxonomy.impl;

import java.util.Collections;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericInstanceNode;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericTypeNode;

/**
 * A generic implementation of a mutable bottom type node of an
 * {@link AbstractDistinctBottomTaxonomy}.
 * <p>
 * This node does not store any members, the members are store in the taxonomy
 * of this node.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of this nodes.
 * @param <I>
 *            The type of members of associated instance nodes.
 * @param <TN>
 *            The immutable type of type nodes with which this node may be
 *            associated.
 * @param <IN>
 *            The immutable type of instance nodes with which this node may be
 *            associated.
 * @param <UTN>
 *            The mutable type of type nodes with which this node may be
 *            associated.
 * @param <UIN>
 *            The mutable type of instance nodes with which this node may be
 *            associated.
 */
public class BottomGenericTypeNode<
				T extends ElkEntity,
				I extends ElkEntity,
				TN extends GenericTypeNode<T, I, TN, IN>,
				IN extends GenericInstanceNode<T, I, TN, IN>,
				UTN extends UpdateableTaxonomyTypeNode<T, I, TN, IN, UTN, UIN>,
				UIN extends UpdateableInstanceNode<T, I, TN, IN, UTN, UIN>
		>
		extends BottomGenericTaxonomyNode<T, TN, UTN>
		implements GenericTypeNode<T, I, TN, IN> {

	/**
	 * Constructor.
	 * 
	 * @param taxonomy
	 *            The taxonomy of this node.
	 * @param bottomMember
	 *            The canonical member of this node.
	 */
	public BottomGenericTypeNode(
			final AbstractDistinctBottomTaxonomy<T, TN, UTN> taxonomy,
			final T bottomMember) {
		super(taxonomy, bottomMember);
	}

	@Override
	public Set<? extends IN> getDirectInstanceNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends IN> getAllInstanceNodes() {
		return Collections.emptySet();
	}

	/**
	 * A subclass with fixed type parameters.
	 * 
	 * @author Peter Skocovsky
	 *
	 * @param <T>
	 *            The type of members of this nodes.
	 * @param <I>
	 *            The type of members of associated instance this nodes.
	 */
	public static class Projection<T extends ElkEntity, I extends ElkEntity>
			extends BottomGenericTypeNode<
					T,
					I,
					GenericTypeNode.Projection<T, I>,
					GenericInstanceNode.Projection<T, I>,
					NonBottomGenericTypeNode.Projection<T, I>,
					IndividualNode.Projection2<T, I>
			> implements GenericTypeNode.Projection<T, I> {

		/**
		 * Constructor.
		 * 
		 * @param taxonomy
		 *            The taxonomy of this node.
		 * @param bottomMember
		 *            The canonical member of this node.
		 */
		public Projection(
				final AbstractDistinctBottomTaxonomy<
						T,
						GenericTypeNode.Projection<T, I>,
						NonBottomGenericTypeNode.Projection<T, I>
				> taxonomy,
				final T bottomMember) {
			super(taxonomy, bottomMember);
		}
		
	}

}
