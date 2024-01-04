
package org.semanticweb.elk.reasoner.indexing.model;

/**
 * An {@link IndexedClassExpression} constructed from two
 * {@link IndexedClassExpression}s.<br>
 * 
 * Notation:
 * 
 * <pre>
 * C ⊓ D
 * </pre>
 * 
 * It is logically equivalent to OWL class expression
 * {@code ObjectIntersectionOf(C D)} <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getFirstConjunct()}<br>
 * D = {@link #getSecondConjunct()}<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface IndexedObjectIntersectionOf extends IndexedClassExpression {

	/**
	 * @return the first conjunction of this {@link IndexedObjectIntersectionOf}
	 */
	IndexedClassExpression getFirstConjunct();

	/**
	 * @return the second conjunction of this
	 *         {@link IndexedObjectIntersectionOf}
	 */
	IndexedClassExpression getSecondConjunct();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedObjectIntersectionOf element);

	}

}
