
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRestriction;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;

/**
 * Implementation of {@link ElkPropertyRestriction}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this restriction
 */
public abstract class ElkPropertyRestrictionImpl<P> extends ElkObjectImpl
		implements ElkPropertyRestriction<P> {

	private final P property_;

	ElkPropertyRestrictionImpl(P property) {
		this.property_ = property;
	}

	@Override
	public P getProperty() {
		return this.property_;
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkPropertyRestrictionVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkPropertyRestrictionVisitor<O>) visitor);
	}

}
