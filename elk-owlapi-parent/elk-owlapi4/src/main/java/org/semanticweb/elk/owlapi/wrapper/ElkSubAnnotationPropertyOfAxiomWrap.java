
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkSubAnnotationPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubAnnotationPropertyOfAxiomVisitor;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;

/**
 * Implements the {@link ElkSubAnnotationPropertyOfAxiom} interface by wrapping
 * instances of {@link OWLSubAnnotationPropertyOfAxiom}
 *
 * @author Frantisek Simancik
 * 
 * @param <T>
 *            the type of the wrapped object
 *
 */
public class ElkSubAnnotationPropertyOfAxiomWrap<T extends OWLSubAnnotationPropertyOfAxiom>
		extends ElkAnnotationAxiomWrap<T> implements
		ElkSubAnnotationPropertyOfAxiom {

	public ElkSubAnnotationPropertyOfAxiomWrap(T owlSubAnnotationPropertyOfAxiom) {
		super(owlSubAnnotationPropertyOfAxiom);
	}

	@Override
	public ElkAnnotationProperty getSubAnnotationProperty() {
		return converter.convert(this.owlObject.getSubProperty());
	}

	@Override
	public ElkAnnotationProperty getSuperAnnotationProperty() {
		return converter.convert(this.owlObject.getSuperProperty());
	}

	@Override
	public <O> O accept(ElkSubAnnotationPropertyOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkAnnotationAxiomVisitor<O> visitor) {
		return accept((ElkSubAnnotationPropertyOfAxiomVisitor<O>) visitor);
	}
}