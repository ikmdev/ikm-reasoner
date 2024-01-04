
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;

/**
 * An {@link IndexedPropertyChain} constructed from an
 * {@link IndexedObjectProperty} and another {@link IndexedPropertyChain}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * R∙P
 * </pre>
 * 
 * It is logically equivalent to the OWL sub-property expression
 * {@code ObjectPropertyChain(R P)}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * R = {@link #getFirstProperty()}<br>
 * P = {@link #getSuffixChain()}<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface IndexedComplexPropertyChain extends IndexedPropertyChain {

	/**
	 * @return the {@link IndexedObjectProperty} that represents the first
	 *         {@link ElkObjectProperty} of the {@link ElkObjectPropertyChain}
	 *         represented by this {@link IndexedComplexPropertyChain}.
	 * 
	 * @see ElkObjectPropertyChain#getObjectPropertyExpressions()
	 */
	IndexedObjectProperty getFirstProperty();

	/**
	 * @return {@link IndexedPropertyChain} that represents the sub-chain of the
	 *         {@link ElkObjectPropertyChain} represented by this
	 *         {@link IndexedComplexPropertyChain} without the first element.
	 * 
	 * @see ElkObjectPropertyChain#getObjectPropertyExpressions()
	 */
	IndexedPropertyChain getSuffixChain();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedComplexPropertyChain element);

	}

}
