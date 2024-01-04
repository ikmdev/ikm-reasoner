
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;

abstract class AbstractDisjointSubsumerInference extends AbstractClassInference
		implements DisjointSubsumerInference {

	private final IndexedClassExpressionList disjointExpressions_;

	private final int position_;

	public AbstractDisjointSubsumerInference(IndexedContextRoot destination,
			IndexedClassExpressionList disjointExpressions, int position) {
		super(destination);
		this.disjointExpressions_ = disjointExpressions;
		this.position_ = position;
	}

	public IndexedClassExpressionList getDisjointExpressions() {
		return disjointExpressions_;
	}

	public int getPosition() {
		return position_;
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final DisjointSubsumer getConclusion(
			DisjointSubsumer.Factory factory) {
		return factory.getDisjointSubsumer(getDestination(),
				getDisjointExpressions(), getPosition());
	}

	@Override
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return accept((DisjointSubsumerInference.Visitor<O>) visitor);
	}

}
