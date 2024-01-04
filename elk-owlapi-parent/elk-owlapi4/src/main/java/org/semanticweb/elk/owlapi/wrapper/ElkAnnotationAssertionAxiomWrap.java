
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationSubject;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationValue;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped {@link OWLAnnotationAssertionAxiom}
 */
public class ElkAnnotationAssertionAxiomWrap<T extends OWLAnnotationAssertionAxiom>
		extends ElkAxiomWrap<T> implements ElkAnnotationAssertionAxiom {

	public ElkAnnotationAssertionAxiomWrap(T owlAxiom) {
		super(owlAxiom);
	}

	@Override
	public ElkAnnotationSubject getSubject() {
		return converter.convert(getSubject(owlObject));
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
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
