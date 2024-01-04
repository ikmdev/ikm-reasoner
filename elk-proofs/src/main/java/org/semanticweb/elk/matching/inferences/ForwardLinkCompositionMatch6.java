
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class ForwardLinkCompositionMatch6
		extends AbstractInferenceMatch<ForwardLinkCompositionMatch5>
		implements ForwardLinkMatch3Watch {

	private final IndexedContextRootMatch conclusionExtendedTargetMatch_;

	ForwardLinkCompositionMatch6(ForwardLinkCompositionMatch5 parent,
			ForwardLinkMatch3 conclusionMatch) {
		super(parent);
		this.conclusionExtendedTargetMatch_ = conclusionMatch
				.getExtendedTargetMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getConclusionExtendedTargetMatch() {
		return conclusionExtendedTargetMatch_;
	}

	ForwardLinkMatch3 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch3(
				getParent().getConclusionMatch(factory),
				getConclusionExtendedTargetMatch());
	}

	public ForwardLinkMatch3 getThirdPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch3(
				getParent().getThirdPremiseMatch(factory),
				getConclusionExtendedTargetMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ForwardLinkMatch3Watch.Visitor<O> visitor) {
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

		O visit(ForwardLinkCompositionMatch6 inferenceMatch6);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ForwardLinkCompositionMatch6 getForwardLinkCompositionMatch6(
				ForwardLinkCompositionMatch5 parent,
				ForwardLinkMatch3 conclusionMatch);

	}

}
