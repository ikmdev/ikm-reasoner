
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.ElkMatchException;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class ForwardLinkOfObjectSomeValuesFromMatch2 extends
		LinkOfObjectSomeValuesFromMatch2<ForwardLinkOfObjectSomeValuesFromMatch1>
		implements ForwardLinkMatch2Watch {

	private final IndexedContextRootMatch extendedOriginMatch_;

	ForwardLinkOfObjectSomeValuesFromMatch2(
			ForwardLinkOfObjectSomeValuesFromMatch1 parent,
			SubClassInclusionDecomposedMatch2 premiseMatch) {
		super(parent, premiseMatch);
		if (!getParent().getForwardRelationMatch()
				.equals(getPremiseSuperExpressionMatch().getPropertyMatch())) {
			throw new ElkMatchException(
					parent.getParent().getDecomposedExistential(),
					getPremiseSuperExpressionMatch());
		}
		this.extendedOriginMatch_ = premiseMatch.getExtendedDestinationMatch();
		checkEquals(premiseMatch, getPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getPremiseMatch(factory), getExtendedOriginMatch(),
				getPremiseSuperExpressionMatch());
	}

	public ForwardLinkMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch2(
				getParent().getConclusionMatch(factory),
				getRootMatch(getParent().getParent().getConclusion(factory)
						.getTarget(), factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ForwardLinkMatch2Watch.Visitor<O> visitor) {
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

		O visit(ForwardLinkOfObjectSomeValuesFromMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ForwardLinkOfObjectSomeValuesFromMatch2 getForwardLinkOfObjectSomeValuesFromMatch2(
				ForwardLinkOfObjectSomeValuesFromMatch1 parent,
				SubClassInclusionDecomposedMatch2 premiseMatch);

	}

}
