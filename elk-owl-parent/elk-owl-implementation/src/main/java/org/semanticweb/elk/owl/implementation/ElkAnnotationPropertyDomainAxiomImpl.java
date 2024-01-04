
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyDomainAxiom;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyDomainAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;

/**
 * Implementation of {@link ElkAnnotationPropertyDomainAxiom}.
 * 
 * @author Frantisek Simancik
 *
 */
public class ElkAnnotationPropertyDomainAxiomImpl extends
		ElkPropertyDomainAxiomImpl<ElkAnnotationProperty, ElkIri> implements
		ElkAnnotationPropertyDomainAxiom {

	ElkAnnotationPropertyDomainAxiomImpl(ElkAnnotationProperty property,
			ElkIri domain) {
		super(property, domain);
	}

	@Override
	public <O> O accept(ElkAnnotationAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyDomainAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationPropertyDomainAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
