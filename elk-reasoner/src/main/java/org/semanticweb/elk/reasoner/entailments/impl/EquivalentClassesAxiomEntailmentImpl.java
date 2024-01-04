
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.EquivalentClassesAxiomEntailment;

public class EquivalentClassesAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkEquivalentClassesAxiom>
		implements EquivalentClassesAxiomEntailment {

	public EquivalentClassesAxiomEntailmentImpl(
			final ElkEquivalentClassesAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
