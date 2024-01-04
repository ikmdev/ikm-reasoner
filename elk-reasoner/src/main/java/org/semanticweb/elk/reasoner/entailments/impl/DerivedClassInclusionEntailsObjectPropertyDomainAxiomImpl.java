
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.reasoner.entailments.model.DerivedClassInclusionEntailsObjectPropertyDomainAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.ObjectPropertyDomainAxiomEntailment;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class DerivedClassInclusionEntailsObjectPropertyDomainAxiomImpl extends
		AbstractAxiomEntailmentInference<ElkObjectPropertyDomainAxiom, ObjectPropertyDomainAxiomEntailment>
		implements DerivedClassInclusionEntailsObjectPropertyDomainAxiom {

	private final SubClassInclusionComposed reason_;

	public DerivedClassInclusionEntailsObjectPropertyDomainAxiomImpl(
			final ObjectPropertyDomainAxiomEntailment conclusion,
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
