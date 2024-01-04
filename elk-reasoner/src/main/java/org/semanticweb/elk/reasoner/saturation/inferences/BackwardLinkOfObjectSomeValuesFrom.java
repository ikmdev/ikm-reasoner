
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

//TODO: split on two inferences
/**
 * A {@link ClassInference} producing a {@link BackwardLink} from a
 * {@link SubClassInclusionDecomposed} with
 * {@link SubClassInclusionDecomposed#getSubsumer()} of the type
 * {@link IndexedObjectSomeValuesFrom}.<br>
 * 
 * Notation:
 * 
 * <pre>
 *    [C] ⊑ -∃R.D
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  C ⊑ <∃R>.[D ⊓ ∃R-]
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getConclusionSource()} <br>
 * ∃R.D = {@link #getDecomposedExistential()} (from which R and D can be
 * obtained)<br>
 * D⊓∃R- = {@link #getDestination()}<br>
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 */
public class BackwardLinkOfObjectSomeValuesFrom
		extends AbstractBackwardLinkInference {

	private final IndexedObjectSomeValuesFrom existential_;

	public BackwardLinkOfObjectSomeValuesFrom(IndexedContextRoot inferenceRoot,
			IndexedObjectSomeValuesFrom subsumer) {
		super(IndexedObjectSomeValuesFrom.Helper.getTarget(subsumer),
				subsumer.getProperty(), inferenceRoot);
		existential_ = subsumer;
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getTraceRoot();
	}

	public IndexedObjectSomeValuesFrom getDecomposedExistential() {
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
	 */
	public static interface Visitor<O> {

		public O visit(BackwardLinkOfObjectSomeValuesFrom inference);

	}

}
