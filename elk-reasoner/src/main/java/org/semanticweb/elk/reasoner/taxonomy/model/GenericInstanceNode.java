
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Instance node with parameterized type of nodes with which it may be
 * associated.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the related type nodes.
 * @param <I>
 *            The type of members of this node.
 * @param <TN>
 *            The type of type nodes with which this node may be associated.
 * @param <IN>
 *            The type of instance nodes with which this node may be associated.
 */
public interface GenericInstanceNode<
				T extends ElkEntity,
				I extends ElkEntity,
				TN extends GenericTypeNode<T, I, TN, IN>,
				IN extends GenericInstanceNode<T, I, TN, IN>
		>
		extends InstanceNode<T, I> {

	@Override
	public Set<? extends TN> getDirectTypeNodes();

	@Override
	public Set<? extends TN> getAllTypeNodes();

	public static interface Projection<T extends ElkEntity, I extends ElkEntity>
			extends GenericInstanceNode<
					T,
					I,
					GenericTypeNode.Projection<T, I>,
					Projection<T, I>
			> {
		// Empty.
	}
	
}
