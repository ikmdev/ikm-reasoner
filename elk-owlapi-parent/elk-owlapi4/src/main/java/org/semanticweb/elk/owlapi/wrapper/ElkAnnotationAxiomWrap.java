
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationAxiom;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationAxiom;

/**
 * Implements the {@link ElkAnnotationAxiom} interface by wrapping instances of
 * {@link OWLAnnotationAxiom}
 * 
 * @author Frantisek Simancik
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkAnnotationAxiomWrap<T extends OWLAnnotationAxiom>
		extends ElkAxiomWrap<T> implements ElkAnnotationAxiom {

	public ElkAnnotationAxiomWrap(T owlAnnotationAxiom) {
		super(owlAnnotationAxiom);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationAxiomVisitor<O>) visitor);
	}
}