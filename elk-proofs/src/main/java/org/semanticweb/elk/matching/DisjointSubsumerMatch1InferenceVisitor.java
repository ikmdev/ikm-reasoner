
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.DisjointSubsumerFromSubsumer;
import org.semanticweb.elk.reasoner.saturation.inferences.DisjointSubsumerInference;

class DisjointSubsumerMatch1InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<DisjointSubsumerMatch1>
		implements DisjointSubsumerInference.Visitor<Void> {

	DisjointSubsumerMatch1InferenceVisitor(InferenceMatch.Factory factory,
			DisjointSubsumerMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(DisjointSubsumerFromSubsumer inference) {
		factory.getDisjointSubsumerFromSubsumerMatch1(inference, child);
		return null;
	}

}
