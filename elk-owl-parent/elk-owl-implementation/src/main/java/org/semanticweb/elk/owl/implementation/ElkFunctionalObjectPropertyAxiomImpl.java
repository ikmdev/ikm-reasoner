
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkFunctionalObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkFunctionalObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;

/**
 * Implements {@link ElkFunctionalObjectPropertyAxiom}
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkFunctionalObjectPropertyAxiomImpl extends
		ElkPropertyAxiomImpl<ElkObjectPropertyExpression> implements
		ElkFunctionalObjectPropertyAxiom {

	ElkFunctionalObjectPropertyAxiomImpl(ElkObjectPropertyExpression property) {
		super(property);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkFunctionalObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkFunctionalObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkFunctionalObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
