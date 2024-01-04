
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.PropagationMatch2;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch2;

public class PropagationGeneratedMatch3
		extends AbstractInferenceMatch<PropagationGeneratedMatch2> {

	PropagationGeneratedMatch3(PropagationGeneratedMatch2 parent,
			SubPropertyChainMatch2 thirdPremiseMatch) {
		super(parent);
		checkEquals(thirdPremiseMatch, getThirdPremiseMatch(DEBUG_FACTORY));
	}

	public SubPropertyChainMatch2 getThirdPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch2(
				getParent().getThirdPremiseMatch(factory),
				getParent().getParent().getSubDestinationMatch(), 0);
	}

	public PropagationMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getPropagationMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getParent().getExtendedDestinationMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
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

		O visit(PropagationGeneratedMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		PropagationGeneratedMatch3 getPropagationGeneratedMatch3(
				PropagationGeneratedMatch2 parent,
				SubPropertyChainMatch2 thirdPremiseMatch);

	}

}
