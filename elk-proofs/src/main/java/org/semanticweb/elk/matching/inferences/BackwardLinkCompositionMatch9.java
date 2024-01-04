
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch4;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class BackwardLinkCompositionMatch9
		extends AbstractInferenceMatch<BackwardLinkCompositionMatch8> {

	private final IndexedContextRootMatch extendedConclusionSourceMatch_;

	BackwardLinkCompositionMatch9(BackwardLinkCompositionMatch8 parent,
			BackwardLinkMatch4 firstPremiseMatch) {
		super(parent);
		this.extendedConclusionSourceMatch_ = firstPremiseMatch
				.getExtendedSourceMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedConclusionSourceMatch() {
		return extendedConclusionSourceMatch_;
	}

	BackwardLinkMatch4 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch4(
				getParent().getFirstPremiseMatch(factory),
				getExtendedConclusionSourceMatch());
	}

	public BackwardLinkMatch4 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch4(
				getParent().getParent().getConclusionMatch(factory),
				getExtendedConclusionSourceMatch());
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

		O visit(BackwardLinkCompositionMatch9 inferenceMatch9);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkCompositionMatch9 getBackwardLinkCompositionMatch9(
				BackwardLinkCompositionMatch8 parent,
				BackwardLinkMatch4 firstPremiseMatch);

	}

}
