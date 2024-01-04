 
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.SubClassInconsistencyEntailsSubClassOfAxiom;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;

public class SubClassInconsistencyEntailsSubClassOfAxiomImpl
		extends AbstractSubClassOfAxiomEntailmentInference<ClassInconsistency>
		implements SubClassInconsistencyEntailsSubClassOfAxiom {

	public SubClassInconsistencyEntailsSubClassOfAxiomImpl(
			final SubClassOfAxiomEntailment conclusion,
			final ClassInconsistency reason) {
		super(conclusion, reason);
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
