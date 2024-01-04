
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkCardinalityRestriction;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;

/**
 * Implementation of {@link ElkCardinalityRestriction}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this restriction
 */
public abstract class ElkCardinalityRestrictionImpl<P> extends
		ElkPropertyRestrictionImpl<P> implements ElkCardinalityRestriction<P> {

	private final int cardinality_;

	ElkCardinalityRestrictionImpl(P property, int cardinality) {
		super(property);
		this.cardinality_ = cardinality;
	}

	@Override
	public int getCardinality() {
		return this.cardinality_;
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkCardinalityRestrictionVisitor<O>) visitor);
	}

}
