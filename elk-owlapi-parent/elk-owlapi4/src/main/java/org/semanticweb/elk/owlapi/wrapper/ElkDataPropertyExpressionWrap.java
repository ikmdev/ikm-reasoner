
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;

/**
 * Implements the {@link ElkDataPropertyExpression} interface by wrapping
 * instances of {@link OWLDataPropertyExpression}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkDataPropertyExpressionWrap<T extends OWLDataPropertyExpression>
		extends ElkObjectWrap<T> implements ElkDataPropertyExpression {

	public ElkDataPropertyExpressionWrap(T owlDataPropertyExpression) {
		super(owlDataPropertyExpression);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataPropertyExpressionVisitor<O>) visitor);
	}

}