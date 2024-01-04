
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyDomainAxiom;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyDomainAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;

/**
 * Implements the {@link ElkAnnotationPropertyDomainAxiom} interface by wrapping
 * instances of {@link OWLAnnotationPropertyDomainAxiom}
 * 
 * @author Frantisek Simancik
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkAnnotationPropertyDomainAxiomWrap<T extends OWLAnnotationPropertyDomainAxiom>
		extends ElkAnnotationAxiomWrap<T>
		implements ElkAnnotationPropertyDomainAxiom {

	public ElkAnnotationPropertyDomainAxiomWrap(
			T owlAnnotationPropertyDomainAxiom) {
		super(owlAnnotationPropertyDomainAxiom);
	}

	@Override
	public ElkAnnotationProperty getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkIri getDomain() {
		return converter.convert(getDomain(owlObject));
	}

	@Override
	public <O> O accept(ElkAnnotationPropertyDomainAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkPropertyDomainAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyDomainAxiomVisitor<O>) visitor);
	}
}