
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;

public class BackwardLinkMatch1
		extends AbstractClassConclusionMatch<BackwardLink> {

	private final IndexedContextRootMatch sourceMatch_;

	BackwardLinkMatch1(BackwardLink parent, IndexedContextRootMatch sourceMatch) {
		super(parent);
		this.sourceMatch_ = sourceMatch;
	}

	/**
	 * @return a match for {@link BackwardLink#getSource()}
	 */
	public IndexedContextRootMatch getSourceMatch() {
		return sourceMatch_;
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

		BackwardLinkMatch1 getBackwardLinkMatch1(BackwardLink parent,
				IndexedContextRootMatch sourceMatch);

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

		O visit(BackwardLinkMatch1 conclusionMatch);

	}

}
