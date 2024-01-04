
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;

abstract class AbstractClassInconsistencyInference
		extends AbstractClassInference implements ClassInconsistencyInference {

	protected AbstractClassInconsistencyInference(
			IndexedContextRoot destination) {
		super(destination);
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final ClassInconsistency getConclusion(
			ClassInconsistency.Factory factory) {
		return factory.getContradiction(getDestination());
	}

	@Override
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return accept((ClassInconsistencyInference.Visitor<O>) visitor);
	}

}
