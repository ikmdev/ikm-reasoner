
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyChainVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;

public class ElkObjectPropertyChainImpl extends
		ElkObjectPropertyExpressionListObject implements ElkObjectPropertyChain {

	ElkObjectPropertyChainImpl(
			List<? extends ElkObjectPropertyExpression> properties) {
		super(properties);
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectPropertyChainVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectPropertyChainVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyChainVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
