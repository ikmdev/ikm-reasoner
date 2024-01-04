
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectHasSelf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link ForwardLink} from a
 * {@link SubClassInclusionDecomposed} with
 * {@link SubClassInclusionDecomposed#getSubsumer()} of the type
 * {@link IndexedObjectHasSelf}:<br>
 * 
 * <pre>
 *  [C] ⊑ -∃R.Self
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  [C] ⊑ <∃R>.C
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()} <br>
 * ∃R.Self = {@link #getDecomposedExistential()} (from which R can be obtained)
 * <br>
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ForwardLinkOfObjectHasSelf extends AbstractClassInference
		implements ForwardLinkInference {

	private final IndexedObjectHasSelf existential_;

	public ForwardLinkOfObjectHasSelf(IndexedContextRoot inferenceRoot,
			IndexedObjectHasSelf subsumer) {
		super(inferenceRoot);
		existential_ = subsumer;
	}

	public IndexedObjectHasSelf getDecomposedExistential() {
		return this.existential_;
	}

	public SubClassInclusionDecomposed getPremise(
			SubClassInclusionDecomposed.Factory factory) {
		return factory.getSubClassInclusionDecomposed(getOrigin(),
				existential_);
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public ForwardLink getConclusion(ForwardLink.Factory factory) {
		return factory.getForwardLink(getDestination(),
				existential_.getProperty(), getOrigin());
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
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
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(ForwardLinkInference.Visitor<O> visitor) {
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

		public O visit(ForwardLinkOfObjectHasSelf inference);

	}

}
