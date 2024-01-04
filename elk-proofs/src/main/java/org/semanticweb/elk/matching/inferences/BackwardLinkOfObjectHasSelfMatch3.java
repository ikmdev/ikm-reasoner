
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch4;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class BackwardLinkOfObjectHasSelfMatch3
		extends AbstractInferenceMatch<BackwardLinkOfObjectHasSelfMatch2>
		implements InferenceMatch {

	private final IndexedContextRootMatch extendedTargetMatch_;

	BackwardLinkOfObjectHasSelfMatch3(BackwardLinkOfObjectHasSelfMatch2 parent,
			BackwardLinkMatch3 conclusionMatch) {
		super(parent);
		this.extendedTargetMatch_ = conclusionMatch
				.getExtendedDestinationMatch();
		checkEquals(conclusionMatch, getParentConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedTargetMatch() {
		return extendedTargetMatch_;
	}

	BackwardLinkMatch3 getParentConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch3(
				getParent().getConclusionMatch(factory),
				getExtendedTargetMatch());
	}

	public BackwardLinkMatch4 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch4(getParentConclusionMatch(factory),
				getExtendedTargetMatch());
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

		O visit(BackwardLinkOfObjectHasSelfMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkOfObjectHasSelfMatch3 getBackwardLinkOfObjectHasSelfMatch3(
				BackwardLinkOfObjectHasSelfMatch2 parent,
				BackwardLinkMatch3 conclusionMatch);

	}

}
