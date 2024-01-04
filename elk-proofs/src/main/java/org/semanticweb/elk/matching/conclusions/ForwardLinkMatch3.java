
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;



public class ForwardLinkMatch3
		extends AbstractClassConclusionMatch<ForwardLinkMatch2> {

	private final IndexedContextRootMatch extendedTargetMatch_;

	ForwardLinkMatch3(ForwardLinkMatch2 parent,
			IndexedContextRootMatch extendedTargetMatch) {
		super(parent);
		this.extendedTargetMatch_ = extendedTargetMatch;
	}

	public IndexedContextRootMatch getExtendedTargetMatch() {
		return extendedTargetMatch_;
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

		ForwardLinkMatch3 getForwardLinkMatch3(ForwardLinkMatch2 parent,
				IndexedContextRootMatch extendedTargetMatch);

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

		O visit(ForwardLinkMatch3 conclusionMatch);

	}

}