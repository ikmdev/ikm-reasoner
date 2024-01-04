
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Arrays;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.entailments.model.EntailedIntersectionInconsistencyEntailsDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.entailments.model.DisjointClassesAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;

public class EntailedIntersectionInconsistencyEntailsDisjointClassesAxiomImpl
		extends
		AbstractAxiomEntailmentInference<ElkDisjointClassesAxiom, DisjointClassesAxiomEntailment>
		implements EntailedIntersectionInconsistencyEntailsDisjointClassesAxiom {

	private final List<? extends SubClassOfAxiomEntailment> premises_;

	public EntailedIntersectionInconsistencyEntailsDisjointClassesAxiomImpl(
			final DisjointClassesAxiomEntailment conclusion,
			final List<? extends SubClassOfAxiomEntailment> premises) {
		super(conclusion);
		this.premises_ = premises;
	}

	public EntailedIntersectionInconsistencyEntailsDisjointClassesAxiomImpl(
			final DisjointClassesAxiomEntailment conclusion,
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
