
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyInference;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfDisjointSubsumers;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfObjectComplementOf;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfOwlNothing;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyPropagated;

class ClassInconsistencyMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<ClassInconsistencyMatch1>
		implements ClassInconsistencyInference.Visitor<Void> {

	ClassInconsistencyMatch1InferenceVisitor(InferenceMatch.Factory factory,
			ClassInconsistencyMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ClassInconsistencyOfOwlNothing inference) {
		factory.getClassInconsistencyOfOwlNothingMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(ClassInconsistencyOfDisjointSubsumers inference) {
		factory.getClassInconsistencyOfDisjointSubsumersMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ClassInconsistencyOfObjectComplementOf inference) {
		factory.getClassInconsistencyOfObjectComplementOfMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ClassInconsistencyPropagated inference) {
		factory.getClassInconsistencyPropagatedMatch1(inference, child);
		return null;
	}

}
