
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityVisitor;

/**
 * Implementation of {@link ElkDataExactCardinalityUnqualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataExactCardinalityUnqualifiedImpl extends
		ElkCardinalityRestrictionImpl<ElkDataPropertyExpression> implements
		ElkDataExactCardinalityUnqualified {

	ElkDataExactCardinalityUnqualifiedImpl(
			ElkDataPropertyExpression property, int cardinality) {
		super(property, cardinality);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataExactCardinalityVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataExactCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
