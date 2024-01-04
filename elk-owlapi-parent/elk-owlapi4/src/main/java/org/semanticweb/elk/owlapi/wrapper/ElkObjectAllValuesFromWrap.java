
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectAllValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectAllValuesFromVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;

/**
 * Implements the {@link ElkObjectAllValuesFrom} interface by wrapping instances
 * of {@link OWLObjectAllValuesFrom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectAllValuesFromWrap<T extends OWLObjectAllValuesFrom>
		extends ElkClassExpressionWrap<T> implements ElkObjectAllValuesFrom {

	ElkObjectAllValuesFromWrap(T owlObjectAllValuesFrom) {
		super(owlObjectAllValuesFrom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkClassExpression getFiller() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectAllValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectAllValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkObjectAllValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectAllValuesFromVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
