
package org.semanticweb.elk.reasoner.entailments.impl;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.entailments.DefaultEntailmentVisitor;
import org.semanticweb.elk.reasoner.entailments.model.AxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.OntologyInconsistency;

class EntailmentPrinter extends DefaultEntailmentVisitor<String> {

	public static final EntailmentPrinter INSTANCE = new EntailmentPrinter();

	public static String toString(final Entailment entailment) {
		return entailment.accept(INSTANCE);
	}

	private EntailmentPrinter() {
		// private default constructor
	}

	@Override
	public String defaultVisit(final Entailment entailment) {
		return entailment.toString();
	}

	@Override
	public <A extends ElkAxiom> String defaultAxiomEntailmentVisit(
			final AxiomEntailment<A> axiomEntailment) {
		return axiomEntailment.getAxiom().toString();
	}

	@Override
	public String visit(
			final OntologyInconsistency inconsistentOntologyEntailment) {
		return OntologyInconsistency.class.getSimpleName();
	}

}
