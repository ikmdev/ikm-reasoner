
package org.semanticweb.elk.owl.inferences;



import org.semanticweb.elk.owl.inferences.ElkInference.Factory;

/**
 * A {@link ElkInference.Factory} which methods return {@code null} and instead
 * produce the constructed inferences using the provided
 * {@link ElkInferenceProducer}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkInferenceProducingFactory
		extends ElkInferenceDelegatingFactory {

	private final ElkInferenceProducer inferenceProducer_;

	public ElkInferenceProducingFactory(Factory mainFactory,
			ElkInferenceProducer inferenceProducer) {
		super(mainFactory);
		this.inferenceProducer_ = inferenceProducer;
	}

	public ElkInferenceProducingFactory(
			ElkInferenceProducer inferenceProducer) {
		this(new ElkInferenceBaseFactory(), inferenceProducer);
	}

	@Override
	protected <I extends ElkInference> I filter(I inference) {
		inferenceProducer_.produce(inference);
		return null;
	}

}
