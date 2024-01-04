
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectInverseOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectInverseOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;
import org.semanticweb.owlapi.model.OWLObjectInverseOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * Implements the {@link ElkObjectInverseOf} interface by wrapping instances of
 * {@link OWLObjectProperty}. We cannot wrap {@link OWLObjectInverseOf} because
 * it can be nested. For example, object property expressions such as
 * {@code ObjectInverseOf(ObjectInverseOf r)} are allowed in OWL API, but are
 * not valid in OWL 2 and for this reason are not allowed to be constructed for
 * {@link ElkObjectPropertyExpression}.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectInverseOfWrap<T extends OWLObjectProperty> extends
		ElkObjectPropertyExpressionWrap<T> implements ElkObjectInverseOf {

	public ElkObjectInverseOfWrap(T owlObjectInverseOf) {
		super(owlObjectInverseOf);
	}

	@Override
	public ElkObjectProperty getObjectProperty() {
		return new ElkObjectPropertyWrap<OWLObjectProperty>(owlObject);
	}

	@Override
	public <O> O accept(ElkObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectInverseOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectInverseOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
