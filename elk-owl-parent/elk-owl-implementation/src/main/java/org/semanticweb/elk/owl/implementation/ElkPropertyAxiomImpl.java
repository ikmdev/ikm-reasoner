
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;

/**
 * Implementation of {@link ElkPropertyAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this axiom
 */
public abstract class ElkPropertyAxiomImpl<P> extends ElkObjectImpl implements
		ElkPropertyAxiom<P> {

	private final P property_;

	ElkPropertyAxiomImpl(P property) {
		this.property_ = property;
	}

	@Override
	public P getProperty() {
		return this.property_;
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkPropertyAxiomVisitor<O>) visitor);
	}

}
