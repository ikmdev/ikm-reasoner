 
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;

/**
 * An {@link IndexedClassExpression} constructed from an {@link ElkDataProperty}
 * and an {@link ElkLiteral}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * ∃P.v
 * </pre>
 * 
 * It is logically equivalent to an OWL class expression
 * {@code ObjectDataHasValue(P v)}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * P = {@link #getRelation()}<br>
 * v = {@link #getFiller()}<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface IndexedDataHasValue extends IndexedClassExpression {

	/**
	 * @return the {@link ElkDataProperty} property of the
	 *         {@link ElkDataHasValue} represented by this
	 *         {@link IndexedDataHasValue}
	 * 
	 * 
	 */
	ElkDataProperty getRelation();

	/**
	 * @return the {@link ElkLiteral} filler of the the {@link ElkDataHasValue}
	 *         represented by this {@link IndexedDataHasValue}
	 * 
	 * @see ElkDataHasValue#getFiller()
	 */
	ElkLiteral getFiller();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedDataHasValue element);

	}

}
