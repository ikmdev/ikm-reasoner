
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class BackwardLinkReversedExpandedMatch3
		extends AbstractInferenceMatch<BackwardLinkReversedExpandedMatch2>
		implements BackwardLinkMatch2Watch {

	private final IndexedContextRootMatch destinationMatch_;

	BackwardLinkReversedExpandedMatch3(
			BackwardLinkReversedExpandedMatch2 parent,
			ForwardLinkMatch2 firstPremiseMatch) {
		super(parent);
		this.destinationMatch_ = firstPremiseMatch.getTargetMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
	}

	ForwardLinkMatch2 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch2(
				getParent().getFirstPremiseMatch(factory),
				getDestinationMatch());
	}

	public BackwardLinkMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getParent().getRelationMatch(), getDestinationMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(BackwardLinkMatch2Watch.Visitor<O> visitor) {
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

		O visit(BackwardLinkReversedExpandedMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkReversedExpandedMatch3 getBackwardLinkReversedExpandedMatch3(
				BackwardLinkReversedExpandedMatch2 parent,
				ForwardLinkMatch2 firstPremiseMatch);

	}

}
