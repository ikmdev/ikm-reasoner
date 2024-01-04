
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;

abstract class AbstractSubClassInclusionInference<S extends IndexedClassExpression>
		extends AbstractClassInference implements SubClassInclusionInference {

	private final S subsumer_;

	public AbstractSubClassInclusionInference(IndexedContextRoot subExpression,
			S superExpression) {
		super(subExpression);
		this.subsumer_ = superExpression;
	}

	public S getSubsumer() {
		return subsumer_;
	}

	public final S getConclusionSubsumer() {
		return getSubsumer();
	}

	@Override
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return accept((SubClassInclusionInference.Visitor<O>) visitor);
	}

}
