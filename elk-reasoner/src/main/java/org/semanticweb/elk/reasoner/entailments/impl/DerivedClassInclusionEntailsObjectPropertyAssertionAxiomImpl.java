
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.reasoner.entailments.model.DerivedClassInclusionEntailsObjectPropertyAssertionAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.ObjectPropertyAssertionAxiomEntailment;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class DerivedClassInclusionEntailsObjectPropertyAssertionAxiomImpl
		extends
		AbstractAxiomEntailmentInference<ElkObjectPropertyAssertionAxiom, ObjectPropertyAssertionAxiomEntailment>
		implements DerivedClassInclusionEntailsObjectPropertyAssertionAxiom {

	private final SubClassInclusionComposed reason_;

	public DerivedClassInclusionEntailsObjectPropertyAssertionAxiomImpl(
			final ObjectPropertyAssertionAxiomEntailment conclusion,
			final SubClassInclusionComposed reason) {
		super(conclusion);
		this.reason_ = reason;
	}

	@Override
	public List<? extends Entailment> getPremises() {
		return Collections.emptyList();
	}

	@Override
	public SubClassInclusionComposed getReason() {
		return reason_;
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
