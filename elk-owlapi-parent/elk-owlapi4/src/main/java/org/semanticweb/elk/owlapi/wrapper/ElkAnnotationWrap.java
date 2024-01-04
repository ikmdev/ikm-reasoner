
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotation;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationValue;
import org.semanticweb.elk.owl.visitors.ElkAnnotationVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLAnnotation;

/**
 * Implements the {@link ElkAnnotation} interface by wrapping instances of
 * {@link OWLAnnotation}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkAnnotationWrap<T extends OWLAnnotation> extends ElkObjectWrap<T>
		implements ElkAnnotation {

	public ElkAnnotationWrap(T annotation) {
		super(annotation);
	}

	@Override
	public ElkAnnotationProperty getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkAnnotationValue getValue() {
		return converter.convert(owlObject.getValue());
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkAnnotationVisitor<O> visitor) {
		return visitor.visit(this);
	}

}