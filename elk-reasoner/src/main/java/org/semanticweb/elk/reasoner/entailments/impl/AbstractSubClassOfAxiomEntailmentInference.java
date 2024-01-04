
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.HasReason;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailmentInference;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;

abstract class AbstractSubClassOfAxiomEntailmentInference<R extends ClassConclusion>
		extends
		AbstractAxiomEntailmentInference<ElkSubClassOfAxiom, SubClassOfAxiomEntailment>
		implements SubClassOfAxiomEntailmentInference, HasReason<R> {

	private final R reason_;

	public AbstractSubClassOfAxiomEntailmentInference(
			final SubClassOfAxiomEntailment conclusion, final R reason) {
		super(conclusion);
		this.reason_ = reason;
	}

	@Override
	public List<? extends Entailment> getPremises() {
		return Collections.emptyList();
	}

	@Override
	public R getReason() {
		return reason_;
	}

}
