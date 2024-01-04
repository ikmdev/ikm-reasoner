
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;

abstract class AbstractSubContextInitializationInference extends
		AbstractSubClassInference implements SubContextInitializationInference {

	protected AbstractSubContextInitializationInference(
			IndexedContextRoot destination,
			IndexedObjectProperty subDestination) {
		super(destination, subDestination);
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final SubContextInitialization getConclusion(
			SubContextInitialization.Factory factory) {
		return factory.getSubContextInitialization(getDestination(),
				getSubDestination());
	}

	@Override
	public final <O> O accept(SubClassInference.Visitor<O> visitor) {
		return accept((SubContextInitializationInference.Visitor<O>) visitor);
	}

}
