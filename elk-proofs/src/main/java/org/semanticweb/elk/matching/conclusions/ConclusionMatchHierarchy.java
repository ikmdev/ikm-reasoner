
package org.semanticweb.elk.matching.conclusions;



public interface ConclusionMatchHierarchy {

	Iterable<? extends BackwardLinkMatch2> getChildren(
			BackwardLinkMatch1 parent);

	Iterable<? extends BackwardLinkMatch3> getChildren(
			BackwardLinkMatch2 parent);

	Iterable<? extends BackwardLinkMatch4> getChildren(
			BackwardLinkMatch3 parent);

	Iterable<? extends ClassInconsistencyMatch2> getChildren(
			ClassInconsistencyMatch1 parent);

	Iterable<? extends DisjointSubsumerMatch2> getChildren(
			DisjointSubsumerMatch1 parent);

	Iterable<? extends ForwardLinkMatch2> getChildren(ForwardLinkMatch1 parent);

	Iterable<? extends ForwardLinkMatch3> getChildren(ForwardLinkMatch2 parent);

	Iterable<? extends ForwardLinkMatch4> getChildren(ForwardLinkMatch3 parent);

	Iterable<? extends IndexedDisjointClassesAxiomMatch2> getChildren(
			IndexedDisjointClassesAxiomMatch1 parent);

	Iterable<? extends IndexedEquivalentClassesAxiomMatch2> getChildren(
			IndexedEquivalentClassesAxiomMatch1 parent);

	Iterable<? extends IndexedObjectPropertyRangeAxiomMatch2> getChildren(
			IndexedObjectPropertyRangeAxiomMatch1 parent);

	Iterable<? extends IndexedSubClassOfAxiomMatch2> getChildren(
			IndexedSubClassOfAxiomMatch1 parent);

	Iterable<? extends IndexedSubObjectPropertyOfAxiomMatch2> getChildren(
			IndexedSubObjectPropertyOfAxiomMatch1 parent);

	Iterable<? extends PropagationMatch2> getChildren(PropagationMatch1 parent);

	Iterable<? extends PropertyRangeMatch2> getChildren(
			PropertyRangeMatch1 parent);

	Iterable<? extends SubClassInclusionComposedMatch2> getChildren(
			SubClassInclusionComposedMatch1 parent);

	Iterable<? extends SubClassInclusionDecomposedMatch2> getChildren(
			SubClassInclusionDecomposedMatch1 parent);

	Iterable<? extends SubPropertyChainMatch2> getChildren(
			SubPropertyChainMatch1 parent);

}
