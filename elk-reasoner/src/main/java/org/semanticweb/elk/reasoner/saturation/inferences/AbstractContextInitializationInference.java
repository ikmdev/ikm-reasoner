
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;

abstract class AbstractContextInitializationInference extends
		AbstractClassInference implements ContextInitializationInference {

	protected AbstractContextInitializationInference(IndexedContextRoot root) {
		super(root);
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final ContextInitialization getConclusion(
			ContextInitialization.Factory factory) {
		return factory.getContextInitialization(getDestination());
	}

	@Override
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return accept((ContextInitializationInference.Visitor<O>) visitor);
	}

}
