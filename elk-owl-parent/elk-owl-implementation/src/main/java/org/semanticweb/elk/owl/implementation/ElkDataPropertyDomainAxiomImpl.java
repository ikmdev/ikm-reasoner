
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyDomainAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;

/**
 * Implementation of {@link ElkDataPropertyDomainAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public class ElkDataPropertyDomainAxiomImpl
		extends
		ElkPropertyDomainAxiomImpl<ElkDataPropertyExpression, ElkClassExpression>
		implements ElkDataPropertyDomainAxiom {

	ElkDataPropertyDomainAxiomImpl(ElkDataPropertyExpression property,
			ElkClassExpression domain) {
		super(property, domain);
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
	public <O> O accept(ElkDataPropertyDomainAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
