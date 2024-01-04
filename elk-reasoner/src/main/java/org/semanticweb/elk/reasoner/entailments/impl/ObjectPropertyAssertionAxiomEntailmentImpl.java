
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.ObjectPropertyAssertionAxiomEntailment;

public class ObjectPropertyAssertionAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkObjectPropertyAssertionAxiom>
		implements ObjectPropertyAssertionAxiomEntailment {

	public ObjectPropertyAssertionAxiomEntailmentImpl(
			final ElkObjectPropertyAssertionAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
