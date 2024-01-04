
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionComposed} with
 * {@link SubClassInclusionComposed#getSubsumer()} instance of
 * {@link IndexedObjectSomeValuesFrom} from a {@link BackwardLink} and a
 * {@link Propagation}:<br>
 * 
 * <pre>
 *   (1)                (2)
 *  C ⊑ <∃R>.[D]  ∃[R].[D] ⊑ ∃S.E
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *          [C] ⊑ +∃S.E
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * R = {@link #getPropagationRelation()}<br>
 * D = {@link #getOrigin()}<br>
 * ∃S.E = {@link #getConclusionSubsumer()} (from which S and E can be obtained)
 * <br>
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 */
public class SubClassInclusionComposedObjectSomeValuesFrom extends
		AbstractSubClassInclusionComposedInference<IndexedObjectSomeValuesFrom> {

	private final IndexedObjectProperty propagationRelation_;

	private final IndexedContextRoot inferenceRoot_;

	public SubClassInclusionComposedObjectSomeValuesFrom(Propagation premise,
			IndexedContextRoot conclusionRoot) {
		super(conclusionRoot, premise.getCarry());
		inferenceRoot_ = premise.getDestination();
		propagationRelation_ = premise.getSubDestination();
	}

	public SubClassInclusionComposedObjectSomeValuesFrom(BackwardLink premise,
			IndexedObjectSomeValuesFrom carry) {
		super(premise.getTraceRoot(), carry);
		inferenceRoot_ = premise.getDestination();
		propagationRelation_ = premise.getRelation();
	}

	public IndexedObjectProperty getPropagationRelation() {
		return propagationRelation_;
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return inferenceRoot_;
	}

	public BackwardLink getFirstPremise(BackwardLink.Factory factory) {
		return factory.getBackwardLink(getOrigin(), propagationRelation_,
				getDestination());
	}

	public Propagation getSecondPremise(Propagation.Factory factory) {
		return factory.getPropagation(getOrigin(), propagationRelation_,
				getSubsumer());
	}

	@Override
	public int getPremiseCount() {
		return 2;
	}

	@Override
	public Conclusion getPremise(int index, Factory factory) {
		switch (index) {
		case 0:
			return getFirstPremise(factory);
		case 1:
			return getSecondPremise(factory);
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

		public O visit(SubClassInclusionComposedObjectSomeValuesFrom inference);

	}

}
