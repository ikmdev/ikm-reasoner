
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkDataHasValueVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;

/**
 * ELK implementation of {@link ElkDataHasValue}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataHasValueImpl
		extends
		ElkPropertyRestrictionQualifiedImpl<ElkDataPropertyExpression, ElkLiteral>
		implements ElkDataHasValue {

	ElkDataHasValueImpl(ElkDataPropertyExpression property,
			ElkLiteral value) {
		super(property, value);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataHasValueVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataHasValueVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
