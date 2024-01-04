
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;

/**
 * Visitor pattern interface for instances of {@link ElkDataPropertyExpression}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataPropertyExpressionVisitor<O> extends
		ElkDataPropertyVisitor<O> {

	// combined visitor

}
