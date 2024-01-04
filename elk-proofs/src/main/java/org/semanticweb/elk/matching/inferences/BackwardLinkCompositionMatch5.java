
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch2;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;

public class BackwardLinkCompositionMatch5
		extends AbstractInferenceMatch<BackwardLinkCompositionMatch4>
		implements ForwardLinkMatch1Watch {

	private final ElkSubObjectPropertyExpression fullPremiseForwardChainMatch_;

	private final int premiseForwardChainStartPos_;

	BackwardLinkCompositionMatch5(BackwardLinkCompositionMatch4 parent,
			SubPropertyChainMatch2 fourthPremiseMatch) {
		super(parent);
		this.fullPremiseForwardChainMatch_ = fourthPremiseMatch
				.getFullSubChainMatch();
		this.premiseForwardChainStartPos_ = fourthPremiseMatch
				.getSubChainStartPos();
		checkEquals(fourthPremiseMatch, getFourthPremiseMatch(DEBUG_FACTORY));
	}

	public ElkSubObjectPropertyExpression getPremiseFullForwardChainMatch() {
		return fullPremiseForwardChainMatch_;
	}

	public int getPremiseForwardChainStartPos() {
		return premiseForwardChainStartPos_;
	}

	public ForwardLinkMatch1 getThirdPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch1(
				getParent().getParent().getParent().getParent().getParent()
						.getThirdPremise(factory),
				getParent().getParent().getOriginMatch(),
				fullPremiseForwardChainMatch_, premiseForwardChainStartPos_);
	}

	SubPropertyChainMatch2 getFourthPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch2(
				getParent().getFourthPremiseMatch(factory),
				getPremiseFullForwardChainMatch(),
				getPremiseForwardChainStartPos());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ForwardLinkMatch1Watch.Visitor<O> visitor) {
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

		O visit(BackwardLinkCompositionMatch5 inferenceMatch5);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkCompositionMatch5 getBackwardLinkCompositionMatch5(
				BackwardLinkCompositionMatch4 parent,
				SubPropertyChainMatch2 fourthPremiseMatch);

	}

}
