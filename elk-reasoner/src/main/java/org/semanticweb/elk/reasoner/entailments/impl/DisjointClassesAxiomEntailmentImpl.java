
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.entailments.model.DisjointClassesAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;

public class DisjointClassesAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkDisjointClassesAxiom>
		implements DisjointClassesAxiomEntailment {

	public DisjointClassesAxiomEntailmentImpl(
			final ElkDisjointClassesAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
