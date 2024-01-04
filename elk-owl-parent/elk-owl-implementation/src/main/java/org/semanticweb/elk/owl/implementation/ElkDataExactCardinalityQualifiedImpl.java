
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityVisitor;

/**
 * Implementation of {@link ElkDataExactCardinalityQualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataExactCardinalityQualifiedImpl
		extends
		ElkCardinalityRestrictionQualifiedImpl<ElkDataPropertyExpression, ElkDataRange>
		implements ElkDataExactCardinalityQualified {

	ElkDataExactCardinalityQualifiedImpl(
			ElkDataPropertyExpression property, int cardinality,
			ElkDataRange range) {
		super(property, cardinality, range);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataExactCardinalityVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataExactCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
