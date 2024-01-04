
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyRangeAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;

/**
 * Implementation of {@link ElkDataPropertyRangeAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public class ElkDataPropertyRangeAxiomImpl extends
		ElkPropertyRangeAxiomImpl<ElkDataPropertyExpression, ElkDataRange>
		implements ElkDataPropertyRangeAxiom {

	ElkDataPropertyRangeAxiomImpl(ElkDataPropertyExpression property,
			ElkDataRange range) {
		super(property, range);
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRangeAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataPropertyRangeAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
