
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

/**
 * A {@link ClassInconsistency} obtained from a
 * {@link SubClassInclusionComposed} premise.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 */
abstract class AbstractClassInconsistencyOfInconsistentSubsumerInference<S extends IndexedClassExpression>
		extends AbstractClassInconsistencyInference {

	private final S premiseSubsumer_;

	AbstractClassInconsistencyOfInconsistentSubsumerInference(
			IndexedContextRoot root, S premiseSubsumer) {
		super(root);
		this.premiseSubsumer_ = premiseSubsumer;
	}

	@Override
	public final IndexedContextRoot getOrigin() {
		return getDestination();
	}

	public final SubClassInclusionComposed getPremise(
			SubClassInclusionComposed.Factory factory) {
		return factory.getSubClassInclusionComposed(getOrigin(),
				premiseSubsumer_);
	}

	final S getPremiseSubsumer() {
		return this.premiseSubsumer_;
	}

}
