
package org.semanticweb.elk.reasoner.indexing.model;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

/**
 * Represents a list of {@link ElkClassExpression}s.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface IndexedClassExpressionList extends IndexedObject {

	/**
	 * @return {@link IndexedClassExpression}s occurring in this
	 *         {@link IndexedClassExpressionList}
	 */
	List<? extends IndexedClassExpression> getElements();

	/**
	 * @return {@code true} if this {@link IndexedClassExpressionList} occurs in
	 *         the ontology
	 */
	boolean occurs();
	
	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedClassExpressionList element);

	}


}
