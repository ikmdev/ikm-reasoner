
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Implementation of {@link ElkClassAssertionAxiom}.
 * 
 * @author Markus Kroetzsch
 * 
 */
public class ElkClassAssertionAxiomImpl extends ElkObjectImpl implements
		ElkClassAssertionAxiom {

	private final ElkIndividual individual_;
	private final ElkClassExpression classExpression_;

	ElkClassAssertionAxiomImpl(ElkClassExpression classExpression,
			ElkIndividual individual) {
		this.individual_ = individual;
		this.classExpression_ = classExpression;
	}

	@Override
	public ElkClassExpression getClassExpression() {
		return classExpression_;
	}

	@Override
	public ElkIndividual getIndividual() {
		return individual_;
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkClassAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkClassAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkClassAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkClassAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
