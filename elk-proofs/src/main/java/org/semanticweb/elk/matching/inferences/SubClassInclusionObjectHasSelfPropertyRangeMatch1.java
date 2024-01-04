
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionObjectHasSelfPropertyRange;

public class SubClassInclusionObjectHasSelfPropertyRangeMatch1 extends
		AbstractInferenceMatch<SubClassInclusionObjectHasSelfPropertyRange>
		implements SubClassInclusionDecomposedMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	SubClassInclusionObjectHasSelfPropertyRangeMatch1(
			SubClassInclusionObjectHasSelfPropertyRange parent,
			SubClassInclusionDecomposedMatch1 conclusionMatch) {
		super(parent);
		originMatch_ = conclusionMatch.getDestinationMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	public SubClassInclusionDecomposedMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch1(
				getParent().getFirstPremise(factory), getOriginMatch());
	}

	SubClassInclusionDecomposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch1(
				getParent().getConclusion(factory), getOriginMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			SubClassInclusionDecomposedMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubClassInclusionObjectHasSelfPropertyRangeMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionObjectHasSelfPropertyRangeMatch1 getSubClassInclusionObjectHasSelfPropertyRangeMatch1(
				SubClassInclusionObjectHasSelfPropertyRange parent,
				SubClassInclusionDecomposedMatch1 conclusionMatch);

	}

}
