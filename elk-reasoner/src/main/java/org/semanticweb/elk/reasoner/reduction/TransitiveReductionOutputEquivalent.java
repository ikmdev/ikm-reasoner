
package org.semanticweb.elk.reasoner.reduction;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

/**
 * The result of the transitive reduction for satisfiable indexed class
 * expression; it contains information about equivalent classes.
 * 
 * @param <R>
 *            the type of the root {@link IndexedClassExpression}s of the
 *            {@link TransitiveReductionJob}s for which this output is computed
 * 
 * @see TransitiveReductionJob
 */
public class TransitiveReductionOutputEquivalent<R extends IndexedClassExpression>
		extends TransitiveReductionOutput<R> {

	private final List<ElkClass> equivalent_;

	TransitiveReductionOutputEquivalent(R root, List<ElkClass> equivalent) {
		super(root);
		this.equivalent_ = equivalent;
	}

	public List<ElkClass> getEquivalent() {
		return equivalent_;
	}

	@Override
	public void accept(TransitiveReductionOutputVisitor<R> visitor) {
		visitor.visit(this);
	}
}
