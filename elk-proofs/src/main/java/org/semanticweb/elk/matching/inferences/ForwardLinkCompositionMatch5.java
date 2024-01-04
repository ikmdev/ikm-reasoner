
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class ForwardLinkCompositionMatch5
		extends AbstractInferenceMatch<ForwardLinkCompositionMatch4>
		implements ForwardLinkMatch2Watch {

	private final IndexedContextRootMatch conclusionTargetMatch_;

	ForwardLinkCompositionMatch5(ForwardLinkCompositionMatch4 parent,
			ForwardLinkMatch2 thirdPremiseMatch) {
		super(parent);
		this.conclusionTargetMatch_ = thirdPremiseMatch.getTargetMatch();
		checkEquals(thirdPremiseMatch, getThirdPremiseMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getConclusionTargetMatch() {
		return conclusionTargetMatch_;
	}

	ForwardLinkMatch2 getThirdPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch2(
				getParent().getThirdPremiseMatch(factory),
				getConclusionTargetMatch());
	}

	public ForwardLinkMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch2(
				getParent().getParent().getParent().getParent()
						.getConclusionMatch(factory),
				getConclusionTargetMatch());
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

		O visit(ForwardLinkCompositionMatch5 inferenceMatch5);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ForwardLinkCompositionMatch5 getForwardLinkCompositionMatch5(
				ForwardLinkCompositionMatch4 parent,
				ForwardLinkMatch2 thirdPremiseMatch);

	}

}
