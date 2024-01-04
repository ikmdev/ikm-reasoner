
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Arrays;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.entailments.model.EntailedClassInclusionCycleEntailsEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.EquivalentClassesAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;

public class EntailedClassInclusionCycleEntailsEquivalentClassesAxiomImpl
		extends
		AbstractAxiomEntailmentInference<ElkEquivalentClassesAxiom, EquivalentClassesAxiomEntailment>
		implements EntailedClassInclusionCycleEntailsEquivalentClassesAxiom {

	private final List<? extends SubClassOfAxiomEntailment> premises_;

	public EntailedClassInclusionCycleEntailsEquivalentClassesAxiomImpl(
			final EquivalentClassesAxiomEntailment conclusion,
			final List<? extends SubClassOfAxiomEntailment> premises) {
		super(conclusion);
		this.premises_ = premises;
	}

	public EntailedClassInclusionCycleEntailsEquivalentClassesAxiomImpl(
			final EquivalentClassesAxiomEntailment conclusion,
			final SubClassOfAxiomEntailment... premises) {
		this(conclusion, Arrays.asList(premises));
	}

	@Override
	public List<? extends SubClassOfAxiomEntailment> getPremises() {
		return premises_;
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
