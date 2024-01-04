
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityVisitor;

/**
 * Implementation of {@link ElkDataMinCardinalityUnqualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataMinCardinalityUnqualifiedImpl extends
		ElkCardinalityRestrictionImpl<ElkDataPropertyExpression> implements
		ElkDataMinCardinalityUnqualified {

	ElkDataMinCardinalityUnqualifiedImpl(ElkDataPropertyExpression property,
			int cardinality) {
		super(property, cardinality);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMinCardinalityVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMinCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
