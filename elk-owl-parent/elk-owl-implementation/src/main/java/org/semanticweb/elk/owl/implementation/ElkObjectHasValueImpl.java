
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectHasValue;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectHasValueVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;

/**
 * Implementation of {@link ElkObjectHasValue}
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectHasValueImpl
		extends
		ElkPropertyRestrictionQualifiedImpl<ElkObjectPropertyExpression, ElkIndividual>
		implements ElkObjectHasValue {

	ElkObjectHasValueImpl(ElkObjectPropertyExpression property,
			ElkIndividual value) {
		super(property, value);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectHasValueVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectHasValueVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
