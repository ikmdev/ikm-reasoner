
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkReflexiveObjectPropertyAxiomVisitor;

/**
 * Implementation of {@link ElkReflexiveObjectPropertyAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkReflexiveObjectPropertyAxiomImpl extends
		ElkPropertyAxiomImpl<ElkObjectPropertyExpression> implements
		ElkReflexiveObjectPropertyAxiom {

	ElkReflexiveObjectPropertyAxiomImpl(ElkObjectPropertyExpression property) {
		super(property);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkReflexiveObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkReflexiveObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkReflexiveObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
