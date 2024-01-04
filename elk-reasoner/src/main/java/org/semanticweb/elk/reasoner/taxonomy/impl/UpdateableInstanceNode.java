
package org.semanticweb.elk.reasoner.taxonomy.impl;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericInstanceNode;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericTypeNode;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceNode;

/**
 * Updateable generic instance node that can be associated with nodes of types
 * of its instances.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of associated type nodes.
 * @param <I>
 *            The type of members of this nodes.
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
public interface UpdateableInstanceNode<
				T extends ElkEntity,
				I extends ElkEntity,
				TN extends GenericTypeNode<T, I, TN, IN>,
				IN extends GenericInstanceNode<T, I, TN, IN>,
				UTN extends UpdateableTypeNode<T, I, TN, IN, UTN, UIN>,
				UIN extends UpdateableInstanceNode<T, I, TN, IN, UTN, UIN>
		>
		extends UpdateableNode<I>, InstanceNode<T, I> {

	/**
	 * Returns an immutable set of all direct type nodes without the bottom
	 * node.
	 * 
	 * @return an immutable set of all direct type nodes without the bottom
	 * node.
	 */
	Set<? extends UTN> getDirectNonBottomTypeNodes();
	
	/**
	 * Associates this node with its direct type node.
	 * 
	 * @param typeNode
	 *            The type node with which this node should be associated.
	 */
	void addDirectTypeNode(UTN typeNode);

	/**
	 * Deletes the association between this node and the specified type node.
	 * 
	 * @param typeNode
	 *            The type node with which this node should not be associated.
	 */
	void removeDirectTypeNode(UTN typeNode);

	/**
	 * A subinterface with fixed type parameters.
	 * 
	 * @author Peter Skocovsky
	 *
	 * @param <T>
	 *            The type of members of associated type nodes.
	 * @param <I>
	 *            The type of members of this nodes.
	 */
	public static interface Projection<T extends ElkEntity, I extends ElkEntity>
			extends UpdateableInstanceNode<
					T,
					I,
					GenericTypeNode.Projection<T, I>,
					GenericInstanceNode.Projection<T, I>,
					UpdateableTypeNode.Projection<T, I>,
					Projection<T, I>
			>, GenericInstanceNode.Projection<T, I>	{
		// Empty.
	}
	
}
