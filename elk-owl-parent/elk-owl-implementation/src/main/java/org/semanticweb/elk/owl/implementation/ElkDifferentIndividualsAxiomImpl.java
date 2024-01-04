
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDifferentIndividualsAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Implementation of {@link ElkDifferentIndividualsAxiom}.
 * 
 * @author Markus Kroetzsch
 */
public class ElkDifferentIndividualsAxiomImpl extends ElkIndividualListObject
		implements ElkDifferentIndividualsAxiom {

	ElkDifferentIndividualsAxiomImpl(List<? extends ElkIndividual> individuals) {
		super(individuals);
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDifferentIndividualsAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDifferentIndividualsAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDifferentIndividualsAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
