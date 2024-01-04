
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkPropertyAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;

/**
 * Implementation of {@link ElkPropertyAssertionAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this assertion
 * @param <S>
 *            the type of the subject of this assertion
 * @param <O>
 *            the type of the object of this assertion
 */
public abstract class ElkPropertyAssertionAxiomImpl<P, S, O> extends
		ElkPropertyAxiomImpl<P> implements ElkPropertyAssertionAxiom<P, S, O> {

	private final S subject_;
	private final O object_;

	ElkPropertyAssertionAxiomImpl(P property, S subject, O object) {
		super(property);
		this.subject_ = subject;
		this.object_ = object;
	}

	@Override
	public S getSubject() {
		return this.subject_;
	}

	@Override
	public O getObject() {
		return this.object_;
	}

	@Override
	public <T> T accept(ElkAxiomVisitor<T> visitor) {
		return accept((ElkPropertyAssertionAxiomVisitor<T>) visitor);
	}

	@Override
	public <T> T accept(ElkPropertyAxiomVisitor<T> visitor) {
		return accept((ElkPropertyAssertionAxiomVisitor<T>) visitor);
	}

	@Override
	public <T> T accept(ElkAssertionAxiomVisitor<T> visitor) {
		return accept((ElkPropertyAssertionAxiomVisitor<T>) visitor);
	}

}
