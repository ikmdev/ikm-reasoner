
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;

public class SubClassOfAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkSubClassOfAxiom>
		implements SubClassOfAxiomEntailment {

	public SubClassOfAxiomEntailmentImpl(final ElkSubClassOfAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
