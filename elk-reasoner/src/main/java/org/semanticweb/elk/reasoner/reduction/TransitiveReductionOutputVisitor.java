
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

/**
 * The visitor pattern interface to distinguish the types of the transitive
 * reduction output.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
/**
 * @author "Yevgeny Kazakov"
 * 
 * @param <R>
 *            the type of the input of the {@link TransitiveReductionJob}s for
 *            which the output can be accepted by this visitor
 */
public interface TransitiveReductionOutputVisitor<R extends IndexedClassExpression> {

	public void visit(TransitiveReductionOutputEquivalentDirect<R> output);

	public void visit(TransitiveReductionOutputEquivalent<R> output);

	public void visit(TransitiveReductionOutputUnsatisfiable<R> output);
}
