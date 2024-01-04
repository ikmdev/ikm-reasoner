
package org.semanticweb.elk.owlapi.proofs;

import org.liveontologies.puli.Inference;
import org.liveontologies.puli.Producer;
import org.semanticweb.elk.owl.inferences.ElkInference;
import org.semanticweb.elk.owl.inferences.ElkInferenceProducer;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * Converts the produced {@link ElkInference}s to {@link OWLAxiom}
 * {@link Inference}s and passes them to the provided {@link Producer}
 * 
 * @author Yevgeny Kazakov
 */
public class ElkInferenceConvertingProducer implements ElkInferenceProducer {

	private final Producer<ElkOwlInference> targetProducer_;

	public ElkInferenceConvertingProducer(
			final Producer<ElkOwlInference> targetProducer) {
		this.targetProducer_ = targetProducer;
	}

	@Override
	public void produce(ElkInference inference) {
		targetProducer_.produce(new ElkOwlInference(inference));
	}

}
