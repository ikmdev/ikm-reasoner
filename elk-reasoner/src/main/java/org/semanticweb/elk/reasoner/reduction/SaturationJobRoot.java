
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

/**
 * A job for computing direct super-classes for the input indexed class
 * expression.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <R>
 *            the type of the root indexed class expression for which transitive
 *            reduction needs to be computed
 * 
 * @param <J>
 *            the type of the transitive reduction job
 */
class SaturationJobRoot<R extends IndexedClassExpression, J extends TransitiveReductionJob<R>>
		extends SaturationJobForTransitiveReduction<R, R, J> {

	final J initiatorJob;

	SaturationJobRoot(J initiatorJob) {
		super(initiatorJob.getInput());
		this.initiatorJob = initiatorJob;
	}

	@Override
	void accept(SaturationJobVisitor<R, J> visitor) throws InterruptedException {
		visitor.visit(this);
	}
}
