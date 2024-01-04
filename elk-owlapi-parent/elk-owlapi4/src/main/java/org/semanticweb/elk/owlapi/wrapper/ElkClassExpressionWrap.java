
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLClassExpression;

/**
 * Implements the {@link ElkClassExpression} interface by wrapping instances of
 * {@link OWLClassExpression}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkClassExpressionWrap<T extends OWLClassExpression>
		extends ElkObjectWrap<T> implements ElkClassExpression {

	ElkClassExpressionWrap(T owlClassExpression) {
		super(owlClassExpression);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkClassExpressionVisitor<O>) visitor);
	}
}
