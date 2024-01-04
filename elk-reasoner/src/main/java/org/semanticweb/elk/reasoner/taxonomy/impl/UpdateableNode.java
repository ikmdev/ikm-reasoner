
package org.semanticweb.elk.reasoner.taxonomy.impl;

import org.semanticweb.elk.reasoner.taxonomy.model.Node;

/**
 * A node that can be modified.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of the members of this node.
 */
public interface UpdateableNode<T> extends Node<T> {

	/**
	 * Atomically sets the <em>allParentsAssigned</em> flag of this node to the
	 * passed argument if the state is different. When the
	 * <em>allParentsAssigned</em> flag of a node is <code>true</code> it is
	 * certain that all parent nodes of this node are set and can be retrieved.
	 * 
	 * @param allParentsAssigned
	 *            How to set the flag.
	 * @return Whether the flag changed.
	 */
	boolean trySetAllParentsAssigned(boolean allParentsAssigned);

	/**
	 * Whether all parent nodes of this node were already assigned.
	 * 
	 * @return The <em>allParentsAssigned</em> flag.
	 */
	boolean areAllParentsAssigned();

	/**
	 * Replaces the members of this node with the specified members.
	 * 
	 * @param members
	 *            The new members of this node.
	 */
	void setMembers(Iterable<? extends T> members);

}
