
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class PropagationGeneratedMatch2
		extends AbstractInferenceMatch<PropagationGeneratedMatch1>
		implements SubPropertyChainMatch1Watch {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	PropagationGeneratedMatch2(PropagationGeneratedMatch1 parent,
			SubClassInclusionComposedMatch2 secondPremiseMatch) {
		super(parent);
		this.extendedDestinationMatch_ = secondPremiseMatch
				.getExtendedDestinationMatch();
		checkEquals(secondPremiseMatch, getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	SubClassInclusionComposedMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getSecondPremiseMatch(factory),
				getExtendedDestinationMatch());
	}

	public SubPropertyChainMatch1 getThirdPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch1(
				getParent().getParent().getThirdPremise(factory),
				getParent().getConclusionCarryMatch().getPropertyMatch(), 0);
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

		O visit(PropagationGeneratedMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		PropagationGeneratedMatch2 getPropagationGeneratedMatch2(
				PropagationGeneratedMatch1 parent,
				SubClassInclusionComposedMatch2 secondPremiseMatch);

	}

}
