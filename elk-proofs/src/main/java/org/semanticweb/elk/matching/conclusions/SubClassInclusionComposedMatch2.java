
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class SubClassInclusionComposedMatch2
		extends AbstractClassConclusionMatch<SubClassInclusionComposedMatch1> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	SubClassInclusionComposedMatch2(SubClassInclusionComposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch) {
		super(parent);
		this.extendedDestinationMatch_ = extendedDestinationMatch;
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
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

		SubClassInclusionComposedMatch2 getSubClassInclusionComposedMatch2(
				SubClassInclusionComposedMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch);

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

		O visit(SubClassInclusionComposedMatch2 conclusionMatch);

	}

}
