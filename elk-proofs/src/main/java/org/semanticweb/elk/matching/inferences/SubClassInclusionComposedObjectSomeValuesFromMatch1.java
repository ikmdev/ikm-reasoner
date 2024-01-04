
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1Watch;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.IndexedObjectSomeValuesFromMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedObjectSomeValuesFrom;

public class SubClassInclusionComposedObjectSomeValuesFromMatch1 extends
		AbstractInferenceMatch<SubClassInclusionComposedObjectSomeValuesFrom>
		implements BackwardLinkMatch1Watch {

	private final IndexedContextRootMatch destinationMatch_;

	private final IndexedObjectSomeValuesFromMatch conclusionSubsumerMatch_;

	SubClassInclusionComposedObjectSomeValuesFromMatch1(
			SubClassInclusionComposedObjectSomeValuesFrom parent,
			SubClassInclusionComposedMatch1 conclusionMatch) {
		super(parent);
		this.destinationMatch_ = conclusionMatch.getDestinationMatch();
		this.conclusionSubsumerMatch_ = conclusionMatch
				.getSubsumerIndexedObjectSomeValuesFromMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
	}

	public IndexedObjectSomeValuesFromMatch getConclusionSubsumerMatch() {
		return conclusionSubsumerMatch_;
	}

	SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getConclusion(factory), destinationMatch_,
				conclusionSubsumerMatch_);
	}

	public BackwardLinkMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch1(
				getParent().getFirstPremise(factory), getDestinationMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(BackwardLinkMatch1Watch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> {

		O visit(SubClassInclusionComposedObjectSomeValuesFromMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedObjectSomeValuesFromMatch1 getSubClassInclusionComposedObjectSomeValuesFromMatch1(
				SubClassInclusionComposedObjectSomeValuesFrom parent,
				SubClassInclusionComposedMatch1 conclusionMatch);

	}

}
