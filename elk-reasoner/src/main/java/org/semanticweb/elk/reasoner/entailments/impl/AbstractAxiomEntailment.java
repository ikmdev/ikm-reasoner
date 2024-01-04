
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.entailments.model.AxiomEntailment;

abstract class AbstractAxiomEntailment<A extends ElkAxiom>
		extends AbstractEntailment implements AxiomEntailment<A> {

	private final A axiom_;

	public AbstractAxiomEntailment(final A axiom) {
		this.axiom_ = axiom;
	}

	@Override
	public A getAxiom() {
		return axiom_;
	}

}
