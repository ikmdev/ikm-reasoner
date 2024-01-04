
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityVisitor;

/**
 * Implementation of {@link ElkObjectMinCardinalityQualified}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectMinCardinalityQualifiedImpl
		extends
		ElkCardinalityRestrictionQualifiedImpl<ElkObjectPropertyExpression, ElkClassExpression>
		implements ElkObjectMinCardinalityQualified {

	ElkObjectMinCardinalityQualifiedImpl(
			ElkObjectPropertyExpression property,
			int cardinality, ElkClassExpression filler) {
		super(property, cardinality, filler);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMinCardinalityVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMinCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
