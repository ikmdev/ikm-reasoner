
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class SubClassInclusionComposedObjectUnionOfMatch2 extends
		AbstractInferenceMatch<SubClassInclusionComposedObjectUnionOfMatch1> {

	private final IndexedContextRootMatch extendedOriginMatch_;

	SubClassInclusionComposedObjectUnionOfMatch2(
			SubClassInclusionComposedObjectUnionOfMatch1 parent,
			SubClassInclusionComposedMatch2 premiseMatch) {
		super(parent);
		this.extendedOriginMatch_ = premiseMatch.getExtendedDestinationMatch();
		checkEquals(premiseMatch, getPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	SubClassInclusionComposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getPremiseMatch(factory), getExtendedOriginMatch());
	}

	public SubClassInclusionComposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getConclusionMatch(factory), extendedOriginMatch_);
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

		O visit(SubClassInclusionComposedObjectUnionOfMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedObjectUnionOfMatch2 getSubClassInclusionComposedObjectUnionOfMatch2(
				SubClassInclusionComposedObjectUnionOfMatch1 parent,
				SubClassInclusionComposedMatch2 premiseMatch);

	}

}
