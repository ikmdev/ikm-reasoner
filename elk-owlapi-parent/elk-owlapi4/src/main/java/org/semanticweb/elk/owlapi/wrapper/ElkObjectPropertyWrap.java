
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * Implements the {@link ElkObjectProperty} interface by wrapping instances of
 * {@link OWLObjectProperty}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectPropertyWrap<T extends OWLObjectProperty> extends
		ElkEntityWrap<T> implements ElkObjectProperty {

	public ElkObjectPropertyWrap(T owlObjectProperty) {
		super(owlObjectProperty);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.OBJECT_PROPERTY;
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
