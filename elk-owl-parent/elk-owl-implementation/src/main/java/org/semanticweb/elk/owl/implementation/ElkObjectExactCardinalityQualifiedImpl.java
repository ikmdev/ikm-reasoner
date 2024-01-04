
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityVisitor;

/**
 * Implementation of {@link ElkObjectExactCardinalityQualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectExactCardinalityQualifiedImpl
		extends
		ElkCardinalityRestrictionQualifiedImpl<ElkObjectPropertyExpression, ElkClassExpression>
		implements ElkObjectExactCardinalityQualified {

	ElkObjectExactCardinalityQualifiedImpl(
			ElkObjectPropertyExpression property,
			int cardinality, ElkClassExpression filler) {
		super(property, cardinality, filler);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectExactCardinalityVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectExactCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
