
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class SubClassInclusionComposedDefinedClassMatch3 extends
		AbstractInferenceMatch<SubClassInclusionComposedDefinedClassMatch2> {

	private final IndexedContextRootMatch extendedOriginMatch_;

	SubClassInclusionComposedDefinedClassMatch3(
			SubClassInclusionComposedDefinedClassMatch2 parent,
			SubClassInclusionComposedMatch2 firstPremiseMatch) {
		super(parent);
		this.extendedOriginMatch_ = firstPremiseMatch
				.getExtendedDestinationMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	public SubClassInclusionComposedMatch2 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getFirstPremiseMatch(factory),
				getExtendedOriginMatch());
	}

	public SubClassInclusionComposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getExtendedOriginMatch());
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

		O visit(SubClassInclusionComposedDefinedClassMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedDefinedClassMatch3 getSubClassInclusionComposedDefinedClassMatch3(
				SubClassInclusionComposedDefinedClassMatch2 parent,
				SubClassInclusionComposedMatch2 firstPremiseMatch);

	}

}
