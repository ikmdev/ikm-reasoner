
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityVisitor;

/**
 * Implementation of {@link ElkDataMaxCardinalityQualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataMaxCardinalityQualifiedImpl
		extends
		ElkCardinalityRestrictionQualifiedImpl<ElkDataPropertyExpression, ElkDataRange>
		implements ElkDataMaxCardinalityQualified {

	ElkDataMaxCardinalityQualifiedImpl(
			ElkDataPropertyExpression property, int cardinality,
			ElkDataRange range) {
		super(property, cardinality, range);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMaxCardinalityVisitor<O> visitor) {
		return accept((ElkDataMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMaxCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
