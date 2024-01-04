
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;

/**
 * A {@link ClassInferenceProducer} that combines two given
 * {@link ClassInferenceProducer}: all methods are executed first for the first
 * {@link ClassInferenceProducer} and then for the second.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class CombinedConclusionProducer implements ClassInferenceProducer {

	private final ClassInferenceProducer firstProducer_;

	private final ClassInferenceProducer secondProducer_;

	public CombinedConclusionProducer(ClassInferenceProducer firstProducer,
			ClassInferenceProducer secondProducer) {
		this.firstProducer_ = firstProducer;
		this.secondProducer_ = secondProducer;
	}

	@Override
	public void produce(ClassInference inference) {
		firstProducer_.produce(inference);
		secondProducer_.produce(inference);
	}

}
