
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectComplementOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkObjectComplementOf.
 * 
 * @author Markus Kroetzsch
 * 
 */
public class ElkObjectComplementOfImpl extends ElkObjectImpl implements
		ElkObjectComplementOf {

	private final ElkClassExpression classExpression_;

	ElkObjectComplementOfImpl(ElkClassExpression negated) {
		this.classExpression_ = negated;
	}

	@Override
	public ElkClassExpression getClassExpression() {
		return classExpression_;
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectComplementOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectComplementOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectComplementOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
