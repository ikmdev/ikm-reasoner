
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectPropertyExpression}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectPropertyExpressionVisitor<O> extends
		ElkObjectInverseOfVisitor<O>, ElkObjectPropertyVisitor<O> {

	// combined visitor

}
