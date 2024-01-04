
package org.semanticweb.elk.reasoner.taxonomy;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.reasoner.taxonomy.model.NodeStore;

/**
 * Implementation with all methods empty.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the nodes in the node store for which this
 *            listener is registered.
 */
public class DummyNodeStoreListener<T extends ElkEntity>
		implements NodeStore.Listener<T> {

	@Override
	public void memberForNodeAppeared(final T member, final Node<T> node) {
		// Empty.
	}

	@Override
	public void memberForNodeDisappeared(final T member, final Node<T> node) {
		// Empty.
	}

}
