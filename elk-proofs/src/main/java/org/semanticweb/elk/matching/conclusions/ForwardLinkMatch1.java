
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;

public class ForwardLinkMatch1
		extends AbstractClassConclusionMatch<ForwardLink> {

	private final IndexedContextRootMatch destinationMatch_;

	private final ElkSubObjectPropertyExpression fullForwardChainMatch_;

	private final int forwardChainStartPos_;

	ForwardLinkMatch1(ForwardLink parent,
			IndexedContextRootMatch destinationMatch,
			ElkSubObjectPropertyExpression fullForwardChainMatch,
			int forwardChainStartPos) {
		super(parent);
		checkChainMatch(fullForwardChainMatch, forwardChainStartPos);
		this.destinationMatch_ = destinationMatch;
		this.fullForwardChainMatch_ = fullForwardChainMatch;
		this.forwardChainStartPos_ = forwardChainStartPos;
	}

	public IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
	}

	public ElkSubObjectPropertyExpression getFullChainMatch() {
		return fullForwardChainMatch_;
	}

	public int getChainStartPos() {
		return forwardChainStartPos_;
	}

	@Override
	public <O> O accept(ClassConclusionMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ForwardLinkMatch1 getForwardLinkMatch1(ForwardLink parent,
				IndexedContextRootMatch destinationMatch,
				ElkSubObjectPropertyExpression fullForwardChainMatch,
				int forwardChainStartPos);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ForwardLinkMatch1 conclusionMatch);

	}

}
