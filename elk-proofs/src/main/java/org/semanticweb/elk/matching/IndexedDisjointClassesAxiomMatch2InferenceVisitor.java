
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch2;
import org.semanticweb.elk.matching.inferences.DisjointSubsumerFromSubsumerMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;

class IndexedDisjointClassesAxiomMatch2InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedDisjointClassesAxiomMatch2>
		implements IndexedDisjointClassesAxiomMatch1Watch.Visitor<Void> {

	IndexedDisjointClassesAxiomMatch2InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedDisjointClassesAxiomMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(DisjointSubsumerFromSubsumerMatch1 inferenceMatch1) {
		factory.getDisjointSubsumerFromSubsumerMatch2(inferenceMatch1, child);
		return null;
	}


}