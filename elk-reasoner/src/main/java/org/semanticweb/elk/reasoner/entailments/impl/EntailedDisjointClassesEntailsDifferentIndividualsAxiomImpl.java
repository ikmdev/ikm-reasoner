 
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.reasoner.entailments.model.DifferentIndividualsAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.DisjointClassesAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailedDisjointClassesEntailsDifferentIndividualsAxiom;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;

public class EntailedDisjointClassesEntailsDifferentIndividualsAxiomImpl extends
		AbstractAxiomEntailmentInference<ElkDifferentIndividualsAxiom, DifferentIndividualsAxiomEntailment>
		implements EntailedDisjointClassesEntailsDifferentIndividualsAxiom {

	private final DisjointClassesAxiomEntailment premise_;

	public EntailedDisjointClassesEntailsDifferentIndividualsAxiomImpl(
			final DifferentIndividualsAxiomEntailment conclusion,
			final DisjointClassesAxiomEntailment premise) {
		super(conclusion);
		this.premise_ = premise;
	}

	@Override
	public List<? extends DisjointClassesAxiomEntailment> getPremises() {
		return Collections.singletonList(premise_);
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
