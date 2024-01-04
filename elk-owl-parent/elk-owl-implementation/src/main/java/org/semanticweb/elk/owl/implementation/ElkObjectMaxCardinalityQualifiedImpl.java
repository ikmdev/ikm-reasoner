
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityVisitor;

/**
 * Implementation of {@link ElkObjectMaxCardinalityQualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectMaxCardinalityQualifiedImpl
		extends
		ElkCardinalityRestrictionQualifiedImpl<ElkObjectPropertyExpression, ElkClassExpression>
		implements ElkObjectMaxCardinalityQualified {

	ElkObjectMaxCardinalityQualifiedImpl(
			ElkObjectPropertyExpression property,
			int cardinality, ElkClassExpression filler) {
		super(property, cardinality, filler);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMaxCardinalityVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMaxCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
