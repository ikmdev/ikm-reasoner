
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRestrictionQualified;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;

/**
 * Implementation of {@link ElkPropertyRestrictionQualified}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this restriction
 * @param <F>
 *            the type of the filler of this restriction
 */
public abstract class ElkPropertyRestrictionQualifiedImpl<P, F> extends
		ElkPropertyRestrictionImpl<P> implements
		ElkPropertyRestrictionQualified<P, F> {

	private final F filler_;

	ElkPropertyRestrictionQualifiedImpl(P property, F filler) {
		super(property);
		this.filler_ = filler;
	}

	@Override
	public F getFiller() {
		return this.filler_;
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkPropertyRestrictionQualifiedVisitor<O>) visitor);
	}

}
