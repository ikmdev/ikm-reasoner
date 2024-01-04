
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch4;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class BackwardLinkOfObjectSomeValuesFromMatch3
		extends AbstractInferenceMatch<BackwardLinkOfObjectSomeValuesFromMatch2>
		implements InferenceMatch {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	BackwardLinkOfObjectSomeValuesFromMatch3(
			BackwardLinkOfObjectSomeValuesFromMatch2 parent,
			BackwardLinkMatch3 conclusionMatch) {
		super(parent);
		this.extendedDestinationMatch_ = conclusionMatch
				.getExtendedDestinationMatch();
		checkEquals(conclusionMatch, getParentConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	BackwardLinkMatch3 getParentConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch3(
				getParent().getConclusionMatch(factory),
				getExtendedDestinationMatch());
	}

	public BackwardLinkMatch4 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch4(getParentConclusionMatch(factory),
				getParent().getExtendedOriginMatch());
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

		O visit(BackwardLinkOfObjectSomeValuesFromMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkOfObjectSomeValuesFromMatch3 getBackwardLinkOfObjectSomeValuesFromMatch3(
				BackwardLinkOfObjectSomeValuesFromMatch2 parent,
				BackwardLinkMatch3 conclusionMatch);

	}

}
