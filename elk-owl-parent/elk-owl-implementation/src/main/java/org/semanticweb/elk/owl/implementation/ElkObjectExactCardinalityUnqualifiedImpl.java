
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityVisitor;

/**
 * Implementation of {@link ElkObjectExactCardinalityUnqualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectExactCardinalityUnqualifiedImpl extends
		ElkCardinalityRestrictionImpl<ElkObjectPropertyExpression> implements
		ElkObjectExactCardinalityUnqualified {

	ElkObjectExactCardinalityUnqualifiedImpl(
			ElkObjectPropertyExpression property,
			int cardinality) {
		super(property, cardinality);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectExactCardinalityVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectExactCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
