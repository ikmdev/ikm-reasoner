
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectSomeValuesFromVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;

/**
 * Implements the {@link ElkObjectSomeValuesFrom} interface by wrapping
 * instances of {@link OWLObjectSomeValuesFrom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectSomeValuesFromWrap<T extends OWLObjectSomeValuesFrom>
		extends ElkClassExpressionWrap<T> implements ElkObjectSomeValuesFrom {

	ElkObjectSomeValuesFromWrap(T owlObjectSomeValuesFrom) {
		super(owlObjectSomeValuesFrom);

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
		return accept((ElkObjectSomeValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectSomeValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkObjectSomeValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectSomeValuesFromVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
