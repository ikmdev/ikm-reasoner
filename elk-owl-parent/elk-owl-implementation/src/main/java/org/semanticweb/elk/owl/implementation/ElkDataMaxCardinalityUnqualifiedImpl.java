
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityVisitor;

/**
 * Implementation of {@link ElkDataMaxCardinalityUnqualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataMaxCardinalityUnqualifiedImpl extends
		ElkCardinalityRestrictionImpl<ElkDataPropertyExpression> implements
		ElkDataMaxCardinalityUnqualified {

	ElkDataMaxCardinalityUnqualifiedImpl(ElkDataPropertyExpression property,
			int cardinality) {
		super(property, cardinality);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkDataMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMaxCardinalityVisitor<O> visitor) {
		return accept((ElkDataMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMaxCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
