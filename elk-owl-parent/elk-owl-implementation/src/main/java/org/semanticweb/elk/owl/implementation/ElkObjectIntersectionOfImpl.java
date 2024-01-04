
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectIntersectionOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkObjectIntersectionOf.
 * 
 * @author Yevgeny Kazakov
 * 
 */
public class ElkObjectIntersectionOfImpl extends ElkClassExpressionListObject
		implements ElkObjectIntersectionOf {

	ElkObjectIntersectionOfImpl(
			List<? extends ElkClassExpression> members) {
		super(members);
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectIntersectionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectIntersectionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectIntersectionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
