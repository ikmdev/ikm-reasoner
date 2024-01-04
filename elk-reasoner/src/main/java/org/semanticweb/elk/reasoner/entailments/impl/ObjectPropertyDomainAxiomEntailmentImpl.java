
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.ObjectPropertyDomainAxiomEntailment;

public class ObjectPropertyDomainAxiomEntailmentImpl
		extends AbstractAxiomEntailment<ElkObjectPropertyDomainAxiom>
		implements ObjectPropertyDomainAxiomEntailment {

	public ObjectPropertyDomainAxiomEntailmentImpl(
			final ElkObjectPropertyDomainAxiom axiom) {
		super(axiom);
	}

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
