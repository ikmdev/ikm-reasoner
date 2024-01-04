
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyVisitor;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.owlapi.model.OWLDataProperty;

/**
 * Implements the {@link ElkDataProperty} interface by wrapping instances of
 * {@link OWLDataProperty}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataPropertyWrap<T extends OWLDataProperty> extends
		ElkEntityWrap<T> implements ElkDataProperty {

	public ElkDataPropertyWrap(T owlDataProperty) {
		super(owlDataProperty);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.DATA_PROPERTY;
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return accept((ElkDataPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataPropertyExpressionVisitor<O> visitor) {
		return accept((ElkDataPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataPropertyVisitor<O> visitor) {
		return visitor.visit(this);
	}

}