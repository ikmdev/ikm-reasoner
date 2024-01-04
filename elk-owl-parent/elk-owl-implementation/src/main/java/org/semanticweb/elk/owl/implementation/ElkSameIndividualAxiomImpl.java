
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSameIndividualAxiomVisitor;

/**
 * ELK implementation of ElkSameIndividualAxiom.
 * 
 * @author Markus Kroetzsch
 */
public class ElkSameIndividualAxiomImpl extends ElkIndividualListObject
		implements ElkSameIndividualAxiom {

	ElkSameIndividualAxiomImpl(List<? extends ElkIndividual> individuals) {
		super(individuals);
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkSameIndividualAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkSameIndividualAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkSameIndividualAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSameIndividualAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
