
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;

/**
 * Visitor pattern interface for instances of
 * {@link ElkSubObjectPropertyExpression}.
 * 
 * @author Frantisek
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkSubObjectPropertyExpressionVisitor<O> extends
		ElkObjectPropertyChainVisitor<O>, ElkObjectPropertyExpressionVisitor<O> {

	// combined visitor

}