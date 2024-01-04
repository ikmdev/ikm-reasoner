
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1Watch;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch3Watch;
import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1;
import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1Watch;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch1;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch1Watch;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1Watch;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3Watch;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.PropagationMatch1;
import org.semanticweb.elk.matching.conclusions.PropagationMatch1Watch;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch1;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1Watch;

public interface InferenceMatchMap extends InferenceMap {

	Iterable<? extends BackwardLinkMatch1Watch> get(
			BackwardLinkMatch1 conclusion);

	Iterable<? extends BackwardLinkMatch2Watch> get(
			BackwardLinkMatch2 conclusion);

	Iterable<? extends BackwardLinkMatch3Watch> get(
			BackwardLinkMatch3 conclusion);

	Iterable<? extends ClassInconsistencyMatch1Watch> get(
			ClassInconsistencyMatch1 conclusion);

	Iterable<? extends DisjointSubsumerMatch1Watch> get(
			DisjointSubsumerMatch1 conclusion);

	Iterable<? extends ForwardLinkMatch1Watch> get(
			ForwardLinkMatch1 conclusion);

	Iterable<? extends ForwardLinkMatch2Watch> get(
			ForwardLinkMatch2 conclusion);

	Iterable<? extends ForwardLinkMatch3Watch> get(
			ForwardLinkMatch3 conclusion);

	Iterable<? extends IndexedDisjointClassesAxiomMatch1Watch> get(
			IndexedDisjointClassesAxiomMatch1 conclusion);

	Iterable<? extends IndexedEquivalentClassesAxiomMatch1Watch> get(
			IndexedEquivalentClassesAxiomMatch1 conclusion);

	Iterable<? extends IndexedObjectPropertyRangeAxiomMatch1Watch> get(
			IndexedObjectPropertyRangeAxiomMatch1 conclusion);

	Iterable<? extends IndexedSubClassOfAxiomMatch1Watch> get(
			IndexedSubClassOfAxiomMatch1 conclusion);

	Iterable<? extends IndexedSubObjectPropertyOfAxiomMatch1Watch> get(
			IndexedSubObjectPropertyOfAxiomMatch1 conclusion);

	Iterable<? extends PropagationMatch1Watch> get(
			PropagationMatch1 conclusion);

	Iterable<? extends PropertyRangeMatch1Watch> get(
			PropertyRangeMatch1 conclusion);

	Iterable<? extends SubClassInclusionComposedMatch1Watch> get(
			SubClassInclusionComposedMatch1 conclusion);

	Iterable<? extends SubClassInclusionDecomposedMatch1Watch> get(
			SubClassInclusionDecomposedMatch1 conclusion);

	Iterable<? extends SubPropertyChainMatch1Watch> get(
			SubPropertyChainMatch1 conclusion);

}
