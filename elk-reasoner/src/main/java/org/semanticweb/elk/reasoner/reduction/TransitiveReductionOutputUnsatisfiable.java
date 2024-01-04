 
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

/**
 * The result of the transitive reduction for unsatisfiable
 * {@link IndexedClassExpression}s. No further information is computed here.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <R>
 *            the type of the root {@link IndexedClassExpression}s of the
 *            {@link TransitiveReductionJob}s for which this output is computed
 * 
 * @see TransitiveReductionJob
 */
public class TransitiveReductionOutputUnsatisfiable<R extends IndexedClassExpression>
		extends TransitiveReductionOutput<R> {

	TransitiveReductionOutputUnsatisfiable(R root) {
		super(root);
	}

	@Override
	public void accept(TransitiveReductionOutputVisitor<R> visitor) {
		visitor.visit(this);
	}

}
