
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectAllValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectAllValuesFromVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;

/**
 * Implementation of {@link ElkObjectAllValuesFrom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectAllValuesFromImpl
		extends
		ElkPropertyRestrictionQualifiedImpl<ElkObjectPropertyExpression, ElkClassExpression>
		implements ElkObjectAllValuesFrom {

	ElkObjectAllValuesFromImpl(
			ElkObjectPropertyExpression property,
			ElkClassExpression filler) {
		super(property, filler);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectAllValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectAllValuesFromVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
