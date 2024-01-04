
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyDomainAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;

/**
 * Implementation of {@link ElkObjectPropertyDomainAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public class ElkObjectPropertyDomainAxiomImpl
		extends
		ElkPropertyDomainAxiomImpl<ElkObjectPropertyExpression, ElkClassExpression>
		implements ElkObjectPropertyDomainAxiom {

	ElkObjectPropertyDomainAxiomImpl(ElkObjectPropertyExpression property,
			ElkClassExpression domain) {
		super(property, domain);
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
	public <O> O accept(ElkObjectPropertyDomainAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
