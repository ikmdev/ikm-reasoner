
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;



public class BackwardLinkMatch2
		extends AbstractClassConclusionMatch<BackwardLinkMatch1> {

	private final ElkObjectProperty relationMatch_;

	private final IndexedContextRootMatch destinationMatch_;

	BackwardLinkMatch2(BackwardLinkMatch1 parent,
			ElkObjectProperty relationMatch,
			IndexedContextRootMatch destinationMatch) {
		super(parent);
		this.relationMatch_ = relationMatch;
		this.destinationMatch_ = destinationMatch;
	}

	/**
	 * @return a match for {@link BackwardLink#getRelation()}
	 */
	public ElkObjectProperty getRelationMatch() {
		return relationMatch_;
	}

	public IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
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

		BackwardLinkMatch2 getBackwardLinkMatch2(BackwardLinkMatch1 parent,
				ElkObjectProperty relationMatch,
				IndexedContextRootMatch destinationMatch);

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

		O visit(BackwardLinkMatch2 conclusionMatch);

	}

}
