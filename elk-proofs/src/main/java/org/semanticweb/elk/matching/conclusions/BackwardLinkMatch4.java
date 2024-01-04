
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;



public class BackwardLinkMatch4
		extends AbstractClassConclusionMatch<BackwardLinkMatch3> {

	private final IndexedContextRootMatch extendedSourceMatch_;

	BackwardLinkMatch4(BackwardLinkMatch3 parent,
			IndexedContextRootMatch extendedSourceMatch) {
		super(parent);
		this.extendedSourceMatch_ = extendedSourceMatch;
	}

	public IndexedContextRootMatch getExtendedSourceMatch() {
		return extendedSourceMatch_;
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

		BackwardLinkMatch4 getBackwardLinkMatch4(BackwardLinkMatch3 parent,
				IndexedContextRootMatch extendedSourceMatch);

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

		O visit(BackwardLinkMatch4 conclusionMatch);

	}

}
