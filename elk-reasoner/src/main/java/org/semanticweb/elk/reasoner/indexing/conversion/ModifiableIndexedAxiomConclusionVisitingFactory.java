
package org.semanticweb.elk.reasoner.indexing.conversion;



import org.liveontologies.puli.Producer;
import org.semanticweb.elk.reasoner.indexing.classes.ModifiableIndexedAxiomInferenceBaseFactory;
import org.semanticweb.elk.reasoner.indexing.classes.ModifiableIndexedAxiomInferenceConclusionVisitor;
import org.semanticweb.elk.reasoner.indexing.classes.ModifiableIndexedAxiomInferenceDelegatingFactory;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObject;

/**
 * A {@link ModifiableIndexedAxiomInference} that, in addition to creating
 * inferences, also creates their conclusions using the provided
 * {@link ModifiableIndexedObject.Factory}
 * 
 * @author Yevgeny Kazakov
 */
class ModifiableIndexedAxiomInferenceConclusionVisitingFactory
		extends ModifiableIndexedAxiomInferenceDelegatingFactory {

	private final Producer<? super IndexedAxiomInference> inferenceProducer_;

	private final ModifiableIndexedAxiomInference.Visitor<Void> inferenceVisitor_;

	ModifiableIndexedAxiomInferenceConclusionVisitingFactory(
			Producer<? super IndexedAxiomInference> inferenceProducer,
			ModifiableIndexedObject.Factory conclusionFactory) {
		super(new ModifiableIndexedAxiomInferenceBaseFactory());
		this.inferenceProducer_ = inferenceProducer;
		this.inferenceVisitor_ = new ModifiableIndexedAxiomInferenceConclusionVisitor<Void>(
				conclusionFactory);
	}

	@Override
	protected <T extends ModifiableIndexedAxiomInference> T filter(T input) {
		inferenceProducer_.produce(input);
		input.accept(inferenceVisitor_);
		return input;
	}

}
