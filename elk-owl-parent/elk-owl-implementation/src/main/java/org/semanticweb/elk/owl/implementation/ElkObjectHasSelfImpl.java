
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectHasSelfVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;

/**
 * Implementation of {@link ElkObjectHasSelf}
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectHasSelfImpl extends
		ElkPropertyRestrictionImpl<ElkObjectPropertyExpression> implements
		ElkObjectHasSelf {

	ElkObjectHasSelfImpl(ElkObjectPropertyExpression property) {
		super(property);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkObjectHasSelfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectHasSelfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
