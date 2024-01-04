
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassEntity;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEntity;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionComposed} from a
 * {@link SubClassInclusionDecomposed} with
 * {@link SubClassInclusionDecomposed#getSubsumer()} instance of
 * {@link IndexedEntity}:<br>
 * 
 * <pre>
 *  [C] ⊑ -A
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  [C] ⊑ +A
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()}<br>
 * A = {@link #getSubsumer()}<br>
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public class SubClassInclusionComposedEntity
		extends AbstractSubClassInclusionComposedInference<IndexedClassEntity> {

	public SubClassInclusionComposedEntity(IndexedContextRoot origin,
			IndexedClassEntity subsumer) {
		super(origin, subsumer);
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
	}

	public SubClassInclusionDecomposed getPremise(
			SubClassInclusionDecomposed.Factory factory) {
		return factory.getSubClassInclusionDecomposed(getOrigin(),
				getSubsumer());
	}

	@Override
	public int getPremiseCount() {
		return 1;
	}

	@Override
	public Conclusion getPremise(int index, Factory factory) {
		switch (index) {
		case 0:
			return getPremise(factory);
		default:
			return failGetPremise(index);
		}
	}

	@Override
	public final <O> O accept(
			SubClassInclusionComposedInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O> {

		public O visit(SubClassInclusionComposedEntity inference);

	}

}
