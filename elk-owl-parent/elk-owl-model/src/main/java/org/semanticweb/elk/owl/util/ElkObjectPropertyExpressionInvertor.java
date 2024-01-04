
package org.semanticweb.elk.owl.util;



import org.semanticweb.elk.owl.interfaces.ElkObjectInverseOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;

/**
 * An {@link ElkObjectPropertyExpressionVisitor} that computes the inverse of
 * the visited {@link ElkObjectPropertyExpression} using the provided
 * {@link ElkObjectInverseOf.Factory}
 * 
 * @author Yevgeny Kazakov
 */
@SuppressWarnings("javadoc")
public class ElkObjectPropertyExpressionInvertor implements
		ElkObjectPropertyExpressionVisitor<ElkObjectPropertyExpression> {

	private final ElkObjectInverseOf.Factory factory_;

	ElkObjectPropertyExpressionInvertor(ElkObjectInverseOf.Factory factory) {
		this.factory_ = factory;
	}

	@Override
	public ElkObjectPropertyExpression visit(ElkObjectInverseOf expression) {
		return expression.getObjectProperty();
	}

	@Override
	public ElkObjectPropertyExpression visit(ElkObjectProperty expression) {
		return factory_.getObjectInverseOf(expression);
	}

	public static ElkObjectPropertyExpression invert(
			ElkObjectPropertyExpression expression,
			ElkObjectInverseOf.Factory factory) {
		return expression
				.accept(new ElkObjectPropertyExpressionInvertor(factory));
	}

}
