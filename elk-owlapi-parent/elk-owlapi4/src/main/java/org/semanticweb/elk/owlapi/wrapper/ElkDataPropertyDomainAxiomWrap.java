
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyDomainAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;

/**
 * Implements the {@link ElkDataPropertyDomainAxiom} interface by wrapping
 * instances of {@link OWLDataPropertyDomainAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataPropertyDomainAxiomWrap<T extends OWLDataPropertyDomainAxiom>
		extends ElkDataPropertyAxiomWrap<T> implements
		ElkDataPropertyDomainAxiom {

	public ElkDataPropertyDomainAxiomWrap(T owlDataPropertyDomainAxiom) {
		super(owlDataPropertyDomainAxiom);
	}

	@Override
	public ElkDataPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkClassExpression getDomain() {
		return converter.convert(getDomain(owlObject));
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyDomainAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataPropertyDomainAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}