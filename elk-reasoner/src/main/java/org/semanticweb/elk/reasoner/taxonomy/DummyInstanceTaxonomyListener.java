
package org.semanticweb.elk.reasoner.taxonomy;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceNode;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.TypeNode;

/**
 * Implementation with all methods empty.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the type nodes in the taxonomy for which
 *            this listener is registered.
 * @param <I>
 *            The type of members of the instance nodes in the taxonomy for
 *            which this listener is registered.
 */
public class DummyInstanceTaxonomyListener<T extends ElkEntity, I extends ElkEntity>
		implements InstanceTaxonomy.Listener<T, I> {

	@Override
	public void directTypeNodesAppeared(final InstanceNode<T, I> instanceNode) {
		// Empty.
	}

	@Override
	public void directTypeNodesDisappeared(
			final InstanceNode<T, I> instanceNode) {
		// Empty.
	}

	@Override
	public void directInstanceNodesAppeared(final TypeNode<T, I> typeNode) {
		// Empty.
	}

	@Override
	public void directInstanceNodesDisappeared(final TypeNode<T, I> typeNode) {
		// Empty.
	}

}
