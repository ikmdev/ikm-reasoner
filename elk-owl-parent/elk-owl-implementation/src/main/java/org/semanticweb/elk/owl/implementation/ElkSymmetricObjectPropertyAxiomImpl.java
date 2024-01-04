
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSymmetricObjectPropertyAxiomVisitor;

/**
 * Implementation of {@link ElkSymmetricObjectPropertyAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkSymmetricObjectPropertyAxiomImpl extends
		ElkPropertyAxiomImpl<ElkObjectPropertyExpression> implements
		ElkSymmetricObjectPropertyAxiom {

	ElkSymmetricObjectPropertyAxiomImpl(ElkObjectPropertyExpression property) {
		super(property);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSymmetricObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
