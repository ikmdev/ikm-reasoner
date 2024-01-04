
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityVisitor;

/**
 * Implementation of {@link ElkObjectMinCardinalityUnqualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectMinCardinalityUnqualifiedImpl extends
		ElkCardinalityRestrictionImpl<ElkObjectPropertyExpression> implements
		ElkObjectMinCardinalityUnqualified {

	ElkObjectMinCardinalityUnqualifiedImpl(
			ElkObjectPropertyExpression property,
			int cardinality) {
		super(property, cardinality);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMinCardinalityVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMinCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
