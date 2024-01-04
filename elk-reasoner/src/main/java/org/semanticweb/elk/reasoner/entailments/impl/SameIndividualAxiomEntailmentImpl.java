
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.SameIndividualAxiomEntailment;

public class SameIndividualAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkSameIndividualAxiom>
		implements SameIndividualAxiomEntailment {

	public SameIndividualAxiomEntailmentImpl(
			final ElkSameIndividualAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
