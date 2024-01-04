
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;

/**
 * Implements the {@link ElkSubObjectPropertyExpression} interface by wrapping
 * instances of objects from OWLAPI
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkSubObjectPropertyExpressionWrap<T>
		extends ElkObjectWrap<T> implements ElkSubObjectPropertyExpression {

	public ElkSubObjectPropertyExpressionWrap(T owlObject) {
		super(owlObject);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkSubObjectPropertyExpressionVisitor<O>) visitor);
	}

}
