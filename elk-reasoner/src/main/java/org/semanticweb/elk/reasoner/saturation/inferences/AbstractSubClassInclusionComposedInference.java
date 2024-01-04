
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

abstract class AbstractSubClassInclusionComposedInference<S extends IndexedClassExpression>
		extends AbstractSubClassInclusionInference<S>
		implements SubClassInclusionComposedInference {

	public AbstractSubClassInclusionComposedInference(
			IndexedContextRoot subExpression, S superExpression) {
		super(subExpression, superExpression);
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final SubClassInclusionComposed getConclusion(
			SubClassInclusionComposed.Factory factory) {
		return factory.getSubClassInclusionComposed(getDestination(),
				getSubsumer());
	}

	@Override
	public final <O> O accept(SubClassInclusionInference.Visitor<O> visitor) {
		return accept((SubClassInclusionComposedInference.Visitor<O>) visitor);
	}

}
