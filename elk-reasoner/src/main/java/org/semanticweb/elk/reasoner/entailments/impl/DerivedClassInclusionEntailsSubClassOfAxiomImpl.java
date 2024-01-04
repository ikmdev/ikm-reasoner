
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.reasoner.entailments.model.DerivedClassInclusionEntailsSubClassOfAxiom;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class DerivedClassInclusionEntailsSubClassOfAxiomImpl extends
		AbstractSubClassOfAxiomEntailmentInference<SubClassInclusionComposed>
		implements DerivedClassInclusionEntailsSubClassOfAxiom {

	public DerivedClassInclusionEntailsSubClassOfAxiomImpl(
			final SubClassOfAxiomEntailment conclusion,
			final SubClassInclusionComposed reason) {
		super(conclusion, reason);
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
