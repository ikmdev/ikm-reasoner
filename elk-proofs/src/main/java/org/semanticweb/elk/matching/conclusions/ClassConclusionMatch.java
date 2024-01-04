
package org.semanticweb.elk.matching.conclusions;



public interface ClassConclusionMatch extends ConclusionMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends BackwardLinkMatch1.Factory, BackwardLinkMatch2.Factory,
			BackwardLinkMatch3.Factory, BackwardLinkMatch4.Factory,
			ClassInconsistencyMatch1.Factory, ClassInconsistencyMatch2.Factory,
			DisjointSubsumerMatch1.Factory, DisjointSubsumerMatch2.Factory,
			SubClassInclusionComposedMatch1.Factory,
			SubClassInclusionComposedMatch2.Factory,
			SubClassInclusionDecomposedMatch1.Factory,
			SubClassInclusionDecomposedMatch2.Factory,
			ForwardLinkMatch1.Factory, ForwardLinkMatch2.Factory,
			ForwardLinkMatch3.Factory, ForwardLinkMatch4.Factory,
			PropagationMatch1.Factory, PropagationMatch2.Factory {

		// combined interface

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkMatch1.Visitor<O>,
			BackwardLinkMatch2.Visitor<O>, BackwardLinkMatch3.Visitor<O>,
			BackwardLinkMatch4.Visitor<O>, ClassInconsistencyMatch1.Visitor<O>,
			ClassInconsistencyMatch2.Visitor<O>,
			DisjointSubsumerMatch1.Visitor<O>,
			DisjointSubsumerMatch2.Visitor<O>,
			SubClassInclusionComposedMatch1.Visitor<O>,
			SubClassInclusionComposedMatch2.Visitor<O>,
			SubClassInclusionDecomposedMatch1.Visitor<O>,
			SubClassInclusionDecomposedMatch2.Visitor<O>,
			ForwardLinkMatch1.Visitor<O>, ForwardLinkMatch2.Visitor<O>,
			ForwardLinkMatch3.Visitor<O>, ForwardLinkMatch4.Visitor<O>,
			PropagationMatch1.Visitor<O>, PropagationMatch2.Visitor<O> {

		// combined interface

	}

}
