
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityVisitor;

/**
 * Implementation of {@link ElkDataMinCardinalityQualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataMinCardinalityQualifiedImpl
		extends
		ElkCardinalityRestrictionQualifiedImpl<ElkDataPropertyExpression, ElkDataRange>
		implements ElkDataMinCardinalityQualified {

	ElkDataMinCardinalityQualifiedImpl(
			ElkDataPropertyExpression property, int cardinality,
			ElkDataRange range) {
		super(property, cardinality, range);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMinCardinalityVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMinCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
