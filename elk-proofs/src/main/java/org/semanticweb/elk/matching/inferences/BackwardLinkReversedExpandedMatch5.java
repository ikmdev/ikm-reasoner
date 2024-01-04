
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch4;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch4;
import org.semanticweb.elk.matching.root.IndexedContextRootMatchChain;

public class BackwardLinkReversedExpandedMatch5
		extends AbstractInferenceMatch<BackwardLinkReversedExpandedMatch4> {

	private final IndexedContextRootMatchChain extendedDomains_;

	BackwardLinkReversedExpandedMatch5(
			BackwardLinkReversedExpandedMatch4 parent,
			ForwardLinkMatch4 firstPremiseMatch) {
		super(parent);
		this.extendedDomains_ = firstPremiseMatch.getExtendedDomains();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatchChain getExtendedDomains() {
		return extendedDomains_;
	}

	ForwardLinkMatch4 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch4(
				getParent().getFirstPremiseMatch(factory),
				getExtendedDomains());
	}

	public BackwardLinkMatch4 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch4(
				getParent().getConclusionMatch(factory),
				getExtendedDomains().getHead());
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

		O visit(BackwardLinkReversedExpandedMatch5 inferenceMatch5);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkReversedExpandedMatch5 getBackwardLinkReversedExpandedMatch5(
				BackwardLinkReversedExpandedMatch4 parent,
				ForwardLinkMatch4 firstPremiseMatch);

	}

}
