
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.TopObjectPropertyInBottomEntailsOntologyInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

public class TopObjectPropertyInBottomEntailsOntologyInconsistencyImpl extends
		AbstractOntologyInconsistencyEntailmentInference<SubPropertyChain>
		implements TopObjectPropertyInBottomEntailsOntologyInconsistency {

	public TopObjectPropertyInBottomEntailsOntologyInconsistencyImpl(
			final SubPropertyChain reason) {
		super(reason);
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
