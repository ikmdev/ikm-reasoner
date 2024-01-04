
package org.semanticweb.elk.reasoner.taxonomy.impl;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericInstanceNode;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericTypeNode;

/**
 * Updateable generic type node that can be associated with nodes of instances
 * of its types and also with other type nodes.
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
public interface UpdateableTaxonomyTypeNode<
				T extends ElkEntity,
				I extends ElkEntity,
				TN extends GenericTypeNode<T, I, TN, IN>,
				IN extends GenericInstanceNode<T, I, TN, IN>,
				UTN extends UpdateableTaxonomyTypeNode<T, I, TN, IN, UTN, UIN>,
				UIN extends UpdateableInstanceNode<T, I, TN, IN, UTN, UIN>
		>
		extends UpdateableTaxonomyNode<T, TN, UTN>,
		UpdateableTypeNode<T, I, TN, IN, UTN, UIN> {

			// combined interface

}
