
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionTautology;

public class SubClassInclusionTautologyMatch1
		extends AbstractInferenceMatch<SubClassInclusionTautology> {

	private final IndexedContextRootMatch originMatch_;

	SubClassInclusionTautologyMatch1(SubClassInclusionTautology parent,
			SubClassInclusionDecomposedMatch1 conclusionMatch) {
		super(parent);
		originMatch_ = conclusionMatch.getDestinationMatch();
		checkEquals(conclusionMatch, getParentConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	public SubClassInclusionDecomposedMatch1 getParentConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch1(
				getParent().getConclusion(factory), getOriginMatch());
	}

	public SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParentConclusionMatch(factory), getOriginMatch(),
				getOriginMatch().getMainFillerMatch(factory));
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

		O visit(SubClassInclusionTautologyMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionTautologyMatch1 getSubClassInclusionTautologyMatch1(
				SubClassInclusionTautology parent,
				SubClassInclusionDecomposedMatch1 conclusionMatch);

	}

}
