
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.entailments.model.AxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.AxiomEntailmentInference;

abstract class AbstractAxiomEntailmentInference<A extends ElkAxiom, E extends AxiomEntailment<? extends A>>
		extends AbstractEntailmentInference
		implements AxiomEntailmentInference<A> {

	private final E conclusion_;

	public AbstractAxiomEntailmentInference(final E conclusion) {
		this.conclusion_ = conclusion;
	}

	@Override
	public E getConclusion() {
		return conclusion_;
	}

}
