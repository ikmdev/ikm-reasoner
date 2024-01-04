
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.IndividualInconsistencyEntailsOntologyInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;

public class IndividualInconsistencyEntailsOntologyInconsistencyImpl extends
		AbstractOntologyInconsistencyEntailmentInference<ClassInconsistency>
		implements IndividualInconsistencyEntailsOntologyInconsistency {

	private final ElkIndividual individual_;

	public IndividualInconsistencyEntailsOntologyInconsistencyImpl(
			final ClassInconsistency reason, final ElkIndividual individual) {
		super(reason);
		this.individual_ = individual;
	}

	@Override
	public ElkIndividual getIndividual() {
		return individual_;
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
