
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.SaturationJob;

/**
 * Instances of saturation jobs for computing super-classes
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <R>
 *            the type of the root indexed class expression for which transitive
 *            reduction needs to be computed
 * @param <I>
 *            the type of the indexed class expression for which saturation is
 *            required to be computed
 * @param <J>
 *            the type of the transitive reduction job
 */
abstract class SaturationJobForTransitiveReduction<R extends IndexedClassExpression, I extends IndexedClassExpression, J extends TransitiveReductionJob<R>>
		extends SaturationJob<I> {

	public SaturationJobForTransitiveReduction(I input) {
		super(input);
	}

	abstract void accept(SaturationJobVisitor<R, J> visitor)
			throws InterruptedException;

}
