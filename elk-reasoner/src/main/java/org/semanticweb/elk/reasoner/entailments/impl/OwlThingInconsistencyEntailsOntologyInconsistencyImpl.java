
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.OwlThingInconsistencyEntailsOntologyInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;

public class OwlThingInconsistencyEntailsOntologyInconsistencyImpl extends
		AbstractOntologyInconsistencyEntailmentInference<ClassInconsistency>
		implements OwlThingInconsistencyEntailsOntologyInconsistency {

	public OwlThingInconsistencyEntailsOntologyInconsistencyImpl(
			final ClassInconsistency reason) {
		super(reason);
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
