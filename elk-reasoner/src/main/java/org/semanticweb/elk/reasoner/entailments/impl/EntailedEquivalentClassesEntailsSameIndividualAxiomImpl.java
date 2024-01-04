
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.reasoner.entailments.model.EntailedEquivalentClassesEntailsSameIndividualAxiom;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.EquivalentClassesAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.SameIndividualAxiomEntailment;

public class EntailedEquivalentClassesEntailsSameIndividualAxiomImpl extends
		AbstractAxiomEntailmentInference<ElkSameIndividualAxiom, SameIndividualAxiomEntailment>
		implements EntailedEquivalentClassesEntailsSameIndividualAxiom {

	private final EquivalentClassesAxiomEntailment premise_;

	public EntailedEquivalentClassesEntailsSameIndividualAxiomImpl(
			final SameIndividualAxiomEntailment conclusion,
			final EquivalentClassesAxiomEntailment premise) {
		super(conclusion);
		this.premise_ = premise;
	}

	@Override
	public List<? extends EquivalentClassesAxiomEntailment> getPremises() {
		return Collections.singletonList(premise_);
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
