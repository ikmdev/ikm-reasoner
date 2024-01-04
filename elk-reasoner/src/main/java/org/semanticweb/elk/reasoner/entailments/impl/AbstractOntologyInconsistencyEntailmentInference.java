
package org.semanticweb.elk.reasoner.entailments.impl;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.HasReason;
import org.semanticweb.elk.reasoner.entailments.model.OntologyInconsistency;
import org.semanticweb.elk.reasoner.entailments.model.OntologyInconsistencyEntailmentInference;

abstract class AbstractOntologyInconsistencyEntailmentInference<C>
		extends AbstractEntailmentInference
		implements OntologyInconsistencyEntailmentInference, HasReason<C> {

	private final C reason_;

	public AbstractOntologyInconsistencyEntailmentInference(final C reason) {
		this.reason_ = reason;
	}

	@Override
	public OntologyInconsistency getConclusion() {
		return OntologyInconsistencyImpl.INSTANCE;
	}

	@Override
	public List<? extends Entailment> getPremises() {
		return Collections.emptyList();
	}

	@Override
	public C getReason() {
		return reason_;
	}

}
