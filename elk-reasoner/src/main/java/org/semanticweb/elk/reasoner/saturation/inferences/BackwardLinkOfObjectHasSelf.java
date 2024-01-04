
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectHasSelf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link BackwardLink} from a
 * {@link SubClassInclusionDecomposed} with
 * {@link SubClassInclusionDecomposed#getSubsumer()} of the type
 * {@link IndexedObjectHasSelf}:<br>
 * 
 * <pre>
 *  [C] ⊑ -∃R.Self
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *    C ⊑ <∃R>.[C]
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getConclusionSource()}=
 * {@link #getDestination()} <br>
 * ∃R.Self = {@link #getDecomposedExistential()} (from which R can be obtained)
 * 
 * @author "Yevgeny Kazakov"
 */
public class BackwardLinkOfObjectHasSelf extends AbstractBackwardLinkInference {

	private final IndexedObjectHasSelf existential_;

	public BackwardLinkOfObjectHasSelf(IndexedContextRoot inferenceRoot,
			IndexedObjectHasSelf subsumer) {
		super(inferenceRoot, subsumer.getProperty(), inferenceRoot);
		existential_ = subsumer;
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
	}

	public IndexedObjectHasSelf getDecomposedExistential() {
		return this.existential_;
	}

	public SubClassInclusionDecomposed getPremise(
			SubClassInclusionDecomposed.Factory factory) {
		return factory.getSubClassInclusionDecomposed(getOrigin(),
				existential_);
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
	public final <O> O accept(BackwardLinkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 * 
	 * @param <O>
	 *            the type of the output
	 *
	 */
	public static interface Visitor<O> {

		public O visit(BackwardLinkOfObjectHasSelf inference);

	}

}
