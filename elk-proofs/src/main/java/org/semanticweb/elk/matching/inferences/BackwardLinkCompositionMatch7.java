
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class BackwardLinkCompositionMatch7
		extends AbstractInferenceMatch<BackwardLinkCompositionMatch6>
		implements ForwardLinkMatch3Watch {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	BackwardLinkCompositionMatch7(BackwardLinkCompositionMatch6 parent,
			BackwardLinkMatch3 conclusionMatch) {
		super(parent);
		this.extendedDestinationMatch_ = conclusionMatch
				.getExtendedDestinationMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	BackwardLinkMatch3 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch3(
				getParent().getConclusionMatch(factory),
				getExtendedDestinationMatch());
	}

	public ForwardLinkMatch3 getThirdPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch3(
				getParent().getThirdPremiseMatch(factory),
				getExtendedDestinationMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ForwardLinkMatch3Watch.Visitor<O> visitor) {
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

		O visit(BackwardLinkCompositionMatch7 inferenceMatch7);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkCompositionMatch7 getBackwardLinkCompositionMatch7(
				BackwardLinkCompositionMatch6 parent,
				BackwardLinkMatch3 conclusionMatch);

	}

}
