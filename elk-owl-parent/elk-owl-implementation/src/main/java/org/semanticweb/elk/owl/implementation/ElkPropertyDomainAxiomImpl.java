
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkPropertyDomainAxiom;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;

/**
 * Implementation of {@link ElkPropertyDomainAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this axiom
 * @param <D>
 *            the type of the domain of this axiom
 */
public abstract class ElkPropertyDomainAxiomImpl<P, D> extends
		ElkPropertyAxiomImpl<P> implements ElkPropertyDomainAxiom<P, D> {

	private final D domain_;

	public ElkPropertyDomainAxiomImpl(P property, D domain) {
		super(property);
		this.domain_ = domain;
	}

	@Override
	public D getDomain() {
		return this.domain_;
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkPropertyDomainAxiomVisitor<O>) visitor);
	}
}
