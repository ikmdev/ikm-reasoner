
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;

/**
 * An {@link IndexedClassExpression} constructed from an
 * {@link IndexedClassExpression}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * ¬C
 * </pre>
 * 
 * It is logically equivalent to OWL class expression
 * {@code ObjectComplementOf(C)} <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getNegated()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedObjectComplementOf extends IndexedClassExpression {

	/**
	 * @return the {@link IndexedClassExpression}, negation of which is
	 *         represented by this {@link IndexedObjectComplementOf}
	 * 
	 * @see ElkObjectComplementOf#getClassExpression()
	 */
	IndexedClassExpression getNegated();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedObjectComplementOf element);

	}

}
