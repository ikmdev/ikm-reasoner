
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 * Implements the {@link ElkObjectPropertyExpression} interface by wrapping
 * instances of {@link OWLObjectPropertyExpression}.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkObjectPropertyExpressionWrap<T extends OWLObjectPropertyExpression>
		extends ElkObjectWrap<T> implements ElkObjectPropertyExpression {

	public ElkObjectPropertyExpressionWrap(T owlObjectPropertyExpression) {
		super(owlObjectPropertyExpression);
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectPropertyExpressionVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectPropertyExpressionVisitor<O>) visitor);
	}

}
