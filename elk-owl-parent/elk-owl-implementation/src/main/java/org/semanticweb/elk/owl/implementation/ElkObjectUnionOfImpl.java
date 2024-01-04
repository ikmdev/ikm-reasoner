
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectUnionOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkObjectUnionOf.
 * 
 * @author Markus Kroetzsch
 * 
 */
public class ElkObjectUnionOfImpl extends ElkClassExpressionListObject
		implements ElkObjectUnionOf {

	ElkObjectUnionOfImpl(List<? extends ElkClassExpression> members) {
		super(members);
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectUnionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectUnionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectUnionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
