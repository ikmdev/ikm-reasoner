
package org.semanticweb.elk.owlapi;

import org.liveontologies.owlapi.proof.OWLProver;
import org.liveontologies.puli.DynamicProof;
import org.semanticweb.elk.owlapi.proofs.ElkOwlInference;
import org.semanticweb.elk.owlapi.proofs.ElkOwlProof;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.reasoner.UnsupportedEntailmentTypeException;

public class ElkProver extends DelegatingOWLReasoner<ElkReasoner> implements OWLProver {

	public ElkProver(ElkReasoner elkReasoner) {
		super(elkReasoner);
	}

	@Override
	public DynamicProof<ElkOwlInference> getProof(OWLAxiom entailment) throws UnsupportedEntailmentTypeException {
		return ElkOwlProof.create(getDelegate(), entailment);
	}

}
