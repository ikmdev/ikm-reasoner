
package org.semanticweb.elk.reasoner.taxonomy;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNode;

/**
 * Implementation with all methods empty.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the nodes in the taxonomy for which this
 *            listener is registered.
 */
public class DummyTaxonomyListener<T extends ElkEntity>
		implements Taxonomy.Listener<T> {

	@Override
	public void directSuperNodesAppeared(final TaxonomyNode<T> subNode) {
		// Empty.
	}

	@Override
	public void directSuperNodesDisappeared(final TaxonomyNode<T> subNode) {
		// Empty.
	}

	@Override
	public void directSubNodesAppeared(final TaxonomyNode<T> superNode) {
		// Empty.
	}

	@Override
	public void directSubNodesDisappeared(final TaxonomyNode<T> superNode) {
		// Empty.
	}

}
