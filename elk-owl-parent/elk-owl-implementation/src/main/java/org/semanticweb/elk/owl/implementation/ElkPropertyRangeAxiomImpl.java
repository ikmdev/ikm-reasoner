
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRangeAxiom;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;

/**
 * Implementation of {@link ElkPropertyRangeAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this axiom
 * @param <R>
 *            the type of the range of this axiom
 */
public abstract class ElkPropertyRangeAxiomImpl<P, R> extends
		ElkPropertyAxiomImpl<P> implements ElkPropertyRangeAxiom<P, R> {

	private final R range_;

	public ElkPropertyRangeAxiomImpl(P property, R range) {
		super(property);
		this.range_ = range;
	}

	@Override
	public R getRange() {
		return this.range_;
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkPropertyRangeAxiomVisitor<O>) visitor);
	}

}
