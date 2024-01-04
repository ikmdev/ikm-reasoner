
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkObjectSomeValuesFromVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;

/**
 * Implementation of {@link ElkObjectSomeValuesFrom}
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 */
public class ElkObjectSomeValuesFromImpl
		extends
		ElkPropertyRestrictionQualifiedImpl<ElkObjectPropertyExpression, ElkClassExpression>
		implements ElkObjectSomeValuesFrom {

	ElkObjectSomeValuesFromImpl(
			ElkObjectPropertyExpression property,
			ElkClassExpression filler) {
		super(property, filler);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectSomeValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectSomeValuesFromVisitor<O> visitor) {
		return visitor.visit(this);
	}
}
