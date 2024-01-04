
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;



public class ForwardLinkMatch2
		extends AbstractClassConclusionMatch<ForwardLinkMatch1> {

	private final IndexedContextRootMatch targetMatch_;

	ForwardLinkMatch2(ForwardLinkMatch1 parent,
			IndexedContextRootMatch targetMatch) {
		super(parent);
		this.targetMatch_ = targetMatch;
	}

	public IndexedContextRootMatch getTargetMatch() {
		return targetMatch_;
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

		ForwardLinkMatch2 getForwardLinkMatch2(ForwardLinkMatch1 parent,
				IndexedContextRootMatch targetMatch);

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

		O visit(ForwardLinkMatch2 conclusionMatch);

	}

}