
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.reasoner.entailments.model.ClassAssertionAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;

public class ClassAssertionAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkClassAssertionAxiom>
		implements ClassAssertionAxiomEntailment {

	public ClassAssertionAxiomEntailmentImpl(
			final ElkClassAssertionAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
