
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyVisitor;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;

/**
 * Implements the {@link ElkAnnotationProperty} interface by wrapping instances
 * of {@link OWLAnnotationProperty}
 * 
 * @author Frantisek Simancik
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkAnnotationPropertyWrap<T extends OWLAnnotationProperty> extends
		ElkEntityWrap<T> implements ElkAnnotationProperty {

	public ElkAnnotationPropertyWrap(T owlAnnotationProperty) {
		super(owlAnnotationProperty);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.ANNOTATION_PROPERTY;
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationPropertyVisitor<O> visitor) {
		return visitor.visit(this);
	}

}