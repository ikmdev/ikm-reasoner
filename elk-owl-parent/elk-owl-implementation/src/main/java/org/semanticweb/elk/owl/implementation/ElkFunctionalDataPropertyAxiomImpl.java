
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkFunctionalDataPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkFunctionalDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;

/**
 * Implements {@link ElkFunctionalDataPropertyAxiom}
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkFunctionalDataPropertyAxiomImpl extends
		ElkPropertyAxiomImpl<ElkDataPropertyExpression> implements
		ElkFunctionalDataPropertyAxiom {

	ElkFunctionalDataPropertyAxiomImpl(ElkDataPropertyExpression property) {
		super(property);
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkFunctionalDataPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkFunctionalDataPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkFunctionalDataPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
