
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

/**
 * The visitor pattern class to distinguish the saturation jobs.
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
interface SaturationJobVisitor<R extends IndexedClassExpression, J extends TransitiveReductionJob<R>> {

	void visit(SaturationJobRoot<R, J> saturationJobTransitiveReductionRoot)
			throws InterruptedException;

	void visit(
			SaturationJobSuperClass<R, J> saturationJobSuperTransitiveReductionClass)
			throws InterruptedException;

}
