
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class BackwardLinkOfObjectSomeValuesFromMatch2 extends
		LinkOfObjectSomeValuesFromMatch2<BackwardLinkOfObjectSomeValuesFromMatch1>
		implements BackwardLinkMatch2Watch {

	private final IndexedContextRootMatch extendedOriginMatch_;

	BackwardLinkOfObjectSomeValuesFromMatch2(
			BackwardLinkOfObjectSomeValuesFromMatch1 parent,
			SubClassInclusionDecomposedMatch2 premiseMatch) {
		super(parent, premiseMatch);
		this.extendedOriginMatch_ = premiseMatch.getExtendedDestinationMatch();
		checkEquals(premiseMatch, getPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getPremiseMatch(factory),
				getExtendedOriginMatch(),
				getPremiseSuperExpressionMatch());
	}

	public BackwardLinkMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch2(
				getParent().getConclusionMatch(factory),
				getPremisePropertyMatch(getParent().getParent()
						.getDecomposedExistential().getProperty()),
				getRootMatch(getParent().getParent().getConclusion(factory)
						.getDestination(), factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(BackwardLinkMatch2Watch.Visitor<O> visitor) {
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

		O visit(BackwardLinkOfObjectSomeValuesFromMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkOfObjectSomeValuesFromMatch2 getBackwardLinkOfObjectSomeValuesFromMatch2(
				BackwardLinkOfObjectSomeValuesFromMatch1 parent,
				SubClassInclusionDecomposedMatch2 premiseMatch);

	}

}
