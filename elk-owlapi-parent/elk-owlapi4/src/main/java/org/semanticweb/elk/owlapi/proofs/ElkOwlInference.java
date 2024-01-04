
package org.semanticweb.elk.owlapi.proofs;



import org.liveontologies.puli.AbstractConvertedInference;
import org.liveontologies.puli.Inference;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owlapi.ElkConverter;
import org.semanticweb.owlapi.model.OWLAxiom;

public class ElkOwlInference
		extends AbstractConvertedInference<ElkAxiom, OWLAxiom>
		implements Inference<OWLAxiom> {

	public ElkOwlInference(Inference<ElkAxiom> elkInference) {
		super(elkInference);
	}

	@Override
	protected OWLAxiom convert(ElkAxiom axiom) {
		return ElkConverter.getInstance().convert(axiom);
	}

}
