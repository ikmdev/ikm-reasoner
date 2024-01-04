 
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.reasoner.entailments.model.DifferentIndividualsAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;

public class DifferentIndividualsAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkDifferentIndividualsAxiom>
		implements DifferentIndividualsAxiomEntailment {

	public DifferentIndividualsAxiomEntailmentImpl(
			final ElkDifferentIndividualsAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
