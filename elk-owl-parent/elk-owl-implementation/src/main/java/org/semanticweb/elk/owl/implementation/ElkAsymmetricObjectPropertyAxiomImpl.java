
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkAsymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAsymmetricObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;

/**
 * Implementation of {@link ElkAsymmetricObjectPropertyAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkAsymmetricObjectPropertyAxiomImpl extends
		ElkPropertyAxiomImpl<ElkObjectPropertyExpression> implements
		ElkAsymmetricObjectPropertyAxiom {

	ElkAsymmetricObjectPropertyAxiomImpl(ElkObjectPropertyExpression property) {
		super(property);
	}

	@Override
	public <O> O accept(ElkAsymmetricObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkAsymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkAsymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

}
