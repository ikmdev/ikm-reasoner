
package org.semanticweb.elk.owlapi;

import org.liveontologies.owlapi.proof.OWLProverFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;

/**
 * An {@link OWLProverFactory} for creating {@link ElkProver}s
 * 
 * @author Yevgeny Kazakov
 */
public class ElkProverFactory implements OWLProverFactory {

	private final ElkReasonerFactory reasonerFactory_ = new ElkReasonerFactory();

	@Override
	public String getReasonerName() {
		return reasonerFactory_.getReasonerName();
	}

	@Override
	public ElkProver createNonBufferingReasoner(OWLOntology ontology) {
		return new ElkProver(reasonerFactory_.createNonBufferingReasoner(ontology));
	}

	@Override
	public ElkProver createReasoner(OWLOntology ontology) {
		return new ElkProver(reasonerFactory_.createReasoner(ontology));
	}

	@Override
	public ElkProver createNonBufferingReasoner(OWLOntology ontology, OWLReasonerConfiguration config) {
		return new ElkProver(reasonerFactory_.createNonBufferingReasoner(ontology, config));
	}

	@Override
	public ElkProver createReasoner(OWLOntology ontology, OWLReasonerConfiguration config) {
		return new ElkProver(reasonerFactory_.createReasoner(ontology, config));
	}

}
