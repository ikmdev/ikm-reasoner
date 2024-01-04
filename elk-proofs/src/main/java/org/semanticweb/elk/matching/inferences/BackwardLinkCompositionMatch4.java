
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch2;

public class BackwardLinkCompositionMatch4
		extends AbstractInferenceMatch<BackwardLinkCompositionMatch3>
		implements SubPropertyChainMatch1Watch {

	BackwardLinkCompositionMatch4(BackwardLinkCompositionMatch3 parent,
			SubPropertyChainMatch2 secondPremiseMatch) {
		super(parent);
		checkEquals(secondPremiseMatch, getSecondPremiseMatch(DEBUG_FACTORY));
	}

	SubPropertyChainMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch2(
				getParent().getSecondPremiseMatch(factory),
				getParent().getPremiseBackwardRelationMatch(), 0);
	}

	public SubPropertyChainMatch1 getFourthPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch1(
				getParent().getParent().getParent().getParent()
						.getFourthPremise(factory),
				getParent().getParent().getCompositionMatch(), 1);
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(SubPropertyChainMatch1Watch.Visitor<O> visitor) {
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

		O visit(BackwardLinkCompositionMatch4 inferenceMatch4);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkCompositionMatch4 getBackwardLinkCompositionMatch4(
				BackwardLinkCompositionMatch3 parent,
				SubPropertyChainMatch2 secondPremiseMatch);

	}

}
