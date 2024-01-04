
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;

public class DisjointSubsumerMatch1
		extends AbstractClassConclusionMatch<DisjointSubsumer> {

	private final IndexedContextRootMatch destinationMatch_;

	DisjointSubsumerMatch1(DisjointSubsumer parent,
			IndexedContextRootMatch destinationMatch) {
		super(parent);
		this.destinationMatch_ = destinationMatch;
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

		DisjointSubsumerMatch1 getDisjointSubsumerMatch1(
				DisjointSubsumer parent,
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

		O visit(DisjointSubsumerMatch1 conclusionMatch);

	}

}
