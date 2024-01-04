
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class PropagationMatch2
		extends AbstractClassConclusionMatch<PropagationMatch1> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	PropagationMatch2(PropagationMatch1 parent,
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

		PropagationMatch2 getPropagationMatch2(PropagationMatch1 parent,
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

		O visit(PropagationMatch2 conclusionMatch);

	}

}
