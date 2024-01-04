
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyRangeAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;

/**
 * Implementation of {@link ElkObjectPropertyRangeAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public class ElkObjectPropertyRangeAxiomImpl
		extends
		ElkPropertyRangeAxiomImpl<ElkObjectPropertyExpression, ElkClassExpression>
		implements ElkObjectPropertyRangeAxiom {

	ElkObjectPropertyRangeAxiomImpl(ElkObjectPropertyExpression property,
			ElkClassExpression range) {
		super(property, range);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRangeAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyRangeAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
