
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectHasSelfVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLObjectHasSelf;

/**
 * Implements the {@link ElkObjectHasSelf} interface by wrapping instances of
 * {@link OWLObjectHasSelf}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectHasSelfWrap<T extends OWLObjectHasSelf> extends
		ElkClassExpressionWrap<T> implements ElkObjectHasSelf {

	ElkObjectHasSelfWrap(T owlObjectHashSelf) {
		super(owlObjectHashSelf);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectHasSelfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkObjectHasSelfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectHasSelfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
