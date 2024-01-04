
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkCardinalityRestrictionQualified;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;

/**
 * Implementation of {@link ElkCardinalityRestrictionQualified}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this restriction
 * @param <F>
 *            the type of the filler of this restriction
 */
public abstract class ElkCardinalityRestrictionQualifiedImpl<P, F> extends
		ElkCardinalityRestrictionImpl<P> implements
		ElkCardinalityRestrictionQualified<P, F> {

	private final F filler_;

	ElkCardinalityRestrictionQualifiedImpl(P property, int cardinality, F filler) {
		super(property, cardinality);
		this.filler_ = filler;
	}

	@Override
	public F getFiller() {
		return this.filler_;
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkCardinalityRestrictionQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkCardinalityRestrictionQualifiedVisitor<O>) visitor);
	}

}
