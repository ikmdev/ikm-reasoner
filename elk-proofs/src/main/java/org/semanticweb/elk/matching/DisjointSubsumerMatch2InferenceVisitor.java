
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch1Watch;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch2;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyOfDisjointSubsumersMatch1;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyOfDisjointSubsumersMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;

class DisjointSubsumerMatch2InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<DisjointSubsumerMatch2>
		implements DisjointSubsumerMatch1Watch.Visitor<Void> {

	DisjointSubsumerMatch2InferenceVisitor(InferenceMatch.Factory factory,
			DisjointSubsumerMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(
			ClassInconsistencyOfDisjointSubsumersMatch1 inferenceMatch1) {
		factory.getClassInconsistencyOfDisjointSubsumersMatch2(inferenceMatch1,
				child);
		return null;
	}

	@Override
	public Void visit(
			ClassInconsistencyOfDisjointSubsumersMatch2 inferenceMatch2) {
		factory.getClassInconsistencyOfDisjointSubsumersMatch3(inferenceMatch2,
				child);
		return null;
	}

}
