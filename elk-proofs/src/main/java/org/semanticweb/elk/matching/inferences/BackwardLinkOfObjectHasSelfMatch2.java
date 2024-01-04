
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

public class BackwardLinkOfObjectHasSelfMatch2
		extends LinkOfObjectHasSelfMatch2<BackwardLinkOfObjectHasSelfMatch1>
		implements InferenceMatch, BackwardLinkMatch2Watch {

	private final IndexedContextRootMatch extendedOriginMatch_;

	BackwardLinkOfObjectHasSelfMatch2(BackwardLinkOfObjectHasSelfMatch1 parent,
			SubClassInclusionDecomposedMatch2 premiseMatch) {
		super(parent, premiseMatch);
		this.extendedOriginMatch_ = premiseMatch.getExtendedDestinationMatch();
		checkEquals(premiseMatch, getPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	public ElkObjectProperty getRelationMatch() {
		return getPremisePropertyMatch(getParent().getParent()
				.getDecomposedExistential().getProperty());
	}

	SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getPremiseMatch(factory), getExtendedOriginMatch(),
				getPremiseSuperExpressionMatch());
	}

	public BackwardLinkMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch2(
				getParent().getConclusionMatch(factory),
				getRelationMatch(), getExtendedOriginMatch());
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

		O visit(BackwardLinkOfObjectHasSelfMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkOfObjectHasSelfMatch2 getBackwardLinkOfObjectHasSelfMatch2(
				BackwardLinkOfObjectHasSelfMatch1 parent,
				SubClassInclusionDecomposedMatch2 premiseMatch);

	}

}
