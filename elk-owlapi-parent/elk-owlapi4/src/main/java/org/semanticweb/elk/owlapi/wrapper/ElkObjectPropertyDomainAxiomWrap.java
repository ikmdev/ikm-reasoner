 
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyDomainAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;

/**
 * Implements the {@link ElkObjectPropertyDomainAxiom} interface by wrapping
 * instances of {@link OWLObjectPropertyDomainAxiom}.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectPropertyDomainAxiomWrap<T extends OWLObjectPropertyDomainAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkObjectPropertyDomainAxiom {

	public ElkObjectPropertyDomainAxiomWrap(T owlObjectPropertyDomainAxiom) {
		super(owlObjectPropertyDomainAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkClassExpression getDomain() {
		return converter.convert(getDomain(owlObject));
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyDomainAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyDomainAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyDomainAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
