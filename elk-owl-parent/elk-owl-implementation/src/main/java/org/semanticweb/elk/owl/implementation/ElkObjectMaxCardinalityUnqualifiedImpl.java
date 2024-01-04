 
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityVisitor;

/**
 * Implementation of {@link ElkObjectMaxCardinalityUnqualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectMaxCardinalityUnqualifiedImpl extends
		ElkCardinalityRestrictionImpl<ElkObjectPropertyExpression> implements
		ElkObjectMaxCardinalityUnqualified {

	ElkObjectMaxCardinalityUnqualifiedImpl(
			ElkObjectPropertyExpression property,
			int cardinality) {
		super(property, cardinality);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMaxCardinalityVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMaxCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
