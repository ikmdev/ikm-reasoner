
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.entailments.model.AxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.OntologyInconsistency;
import org.semanticweb.elk.reasoner.entailments.model.OntologyInconsistencyEntailsAnyAxiom;

public class OntologyInconsistencyEntailsAnyAxiomImpl extends
		AbstractAxiomEntailmentInference<ElkAxiom, AxiomEntailment<? extends ElkAxiom>>
		implements OntologyInconsistencyEntailsAnyAxiom {

	public OntologyInconsistencyEntailsAnyAxiomImpl(
			final AxiomEntailment<? extends ElkAxiom> conclusion) {
		super(conclusion);
	}

	@Override
	public <O> O accept(final EntailmentInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public List<? extends OntologyInconsistency> getPremises() {
		return Collections.singletonList(OntologyInconsistencyImpl.INSTANCE);
	}

}
