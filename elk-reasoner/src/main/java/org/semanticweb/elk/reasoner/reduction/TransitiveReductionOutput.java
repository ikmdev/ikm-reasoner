
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

/**
 * The abstract class representing the output of the transitive reduction
 * process for a given indexed class expression.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <R>
 *            the type of the input for the transitive reduction job
 */
public abstract class TransitiveReductionOutput<R extends IndexedClassExpression> {

	/**
	 * The indexed class expression for which the transitive reduction was
	 * initiated.
	 */
	private final R root_;

	TransitiveReductionOutput(R root) {
		this.root_ = root;
	}

	/**
	 * Returns the indexed class expression for which this output was computed.
	 * 
	 * @return the indexed class expression for which this output was computed
	 */
	public R getRoot() {
		return root_;
	}

	@Override
	public int hashCode() {
		return this.root_.hashCode();
	}

	public abstract void accept(TransitiveReductionOutputVisitor<R> visitor);

}
