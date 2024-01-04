 
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch2;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class DisjointSubsumerFromSubsumerMatch3
		extends AbstractInferenceMatch<DisjointSubsumerFromSubsumerMatch2> {

	private final IndexedContextRootMatch extendedOriginMatch_;

	DisjointSubsumerFromSubsumerMatch3(
			DisjointSubsumerFromSubsumerMatch2 parent,
			SubClassInclusionComposedMatch2 firstPremiseMatch) {
		super(parent);
		this.extendedOriginMatch_ = firstPremiseMatch
				.getExtendedDestinationMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	SubClassInclusionComposedMatch2 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getFirstPremiseMatch(factory),
				getExtendedOriginMatch());
	}

	public DisjointSubsumerMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getDisjointSubsumerMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getExtendedOriginMatch(),
				getParent().getDisjointExpressionsMatch());
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

		O visit(DisjointSubsumerFromSubsumerMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		DisjointSubsumerFromSubsumerMatch3 getDisjointSubsumerFromSubsumerMatch3(
				DisjointSubsumerFromSubsumerMatch2 parent,
				SubClassInclusionComposedMatch2 firstPremiseMatch);

	}

}
