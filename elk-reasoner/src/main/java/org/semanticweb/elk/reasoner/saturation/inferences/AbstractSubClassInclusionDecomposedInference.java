 
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;

abstract class AbstractSubClassInclusionDecomposedInference
		extends AbstractSubClassInclusionInference<IndexedClassExpression>
		implements SubClassInclusionDecomposedInference {

	public AbstractSubClassInclusionDecomposedInference(
			IndexedContextRoot subExpression,
			IndexedClassExpression superExpression) {
		super(subExpression, superExpression);
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final SubClassInclusionDecomposed getConclusion(
			SubClassInclusionDecomposed.Factory factory) {
		return factory.getSubClassInclusionDecomposed(getDestination(),
				getSubsumer());
	}

	@Override
	public final <O> O accept(SubClassInclusionInference.Visitor<O> visitor) {
		return accept(
				(SubClassInclusionDecomposedInference.Visitor<O>) visitor);
	}

}
