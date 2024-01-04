
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch4;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch4;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatchChain;

public class ForwardLinkCompositionMatch8
		extends AbstractInferenceMatch<ForwardLinkCompositionMatch7> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	ForwardLinkCompositionMatch8(ForwardLinkCompositionMatch7 parent,
			BackwardLinkMatch4 firstPremiseMatch) {
		super(parent);
		this.extendedDestinationMatch_ = firstPremiseMatch
				.getExtendedSourceMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	BackwardLinkMatch4 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch4(
				getParent().getFirstPremiseMatch(factory),
				getExtendedDestinationMatch());
	}

	public ForwardLinkMatch4 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		IndexedContextRootMatchChain forwardChainExtendedDomains = getParent()
				.getForwardChainExtendedDomains();
		return factory.getForwardLinkMatch4(
				getParent().getParent().getConclusionMatch(factory),
				new IndexedContextRootMatchChain(getExtendedDestinationMatch(),
						getParent().getParent().getParent().getParent()
								.getPremiseForwardChainStartPos() == 0
										? new IndexedContextRootMatchChain(
												forwardChainExtendedDomains
														.getHead())
										: forwardChainExtendedDomains));
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

		O visit(ForwardLinkCompositionMatch8 inferenceMatch7);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ForwardLinkCompositionMatch8 getForwardLinkCompositionMatch8(
				ForwardLinkCompositionMatch7 parent,
				BackwardLinkMatch4 firstPremiseMatch);

	}

}
