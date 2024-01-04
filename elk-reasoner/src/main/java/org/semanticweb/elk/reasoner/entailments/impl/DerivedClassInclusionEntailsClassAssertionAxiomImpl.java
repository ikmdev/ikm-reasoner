
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.reasoner.entailments.model.ClassAssertionAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.DerivedClassInclusionEntailsClassAssertionAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class DerivedClassInclusionEntailsClassAssertionAxiomImpl extends
		AbstractAxiomEntailmentInference<ElkClassAssertionAxiom, ClassAssertionAxiomEntailment>
		implements DerivedClassInclusionEntailsClassAssertionAxiom {

	private final SubClassInclusionComposed reason_;

	public DerivedClassInclusionEntailsClassAssertionAxiomImpl(
			final ClassAssertionAxiomEntailment conclusion,
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
