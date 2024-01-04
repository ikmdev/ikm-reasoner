
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch2;

public class SubPropertyChainExpandedSubObjectPropertyOfMatch3 extends
		AbstractInferenceMatch<SubPropertyChainExpandedSubObjectPropertyOfMatch2> {

	SubPropertyChainExpandedSubObjectPropertyOfMatch3(
			SubPropertyChainExpandedSubObjectPropertyOfMatch2 parent,
			SubPropertyChainMatch2 secondPremiseMatch) {
		super(parent);
		checkEquals(secondPremiseMatch, getSecondPremiseMatch(DEBUG_FACTORY));
	}

	SubPropertyChainMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch2(
				getParent().getSecondPremiseMatch(factory),
				getParent().getInterPropertyMatch(), 0);
	}

	public SubPropertyChainMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getParent().getSubChainMatch(), 0);
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

		O visit(SubPropertyChainExpandedSubObjectPropertyOfMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubPropertyChainExpandedSubObjectPropertyOfMatch3 getSubPropertyChainExpandedSubObjectPropertyOfMatch3(
				SubPropertyChainExpandedSubObjectPropertyOfMatch2 parent,
				SubPropertyChainMatch2 secondPremiseMatch);

	}

}
