 
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.PropagationMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.PropagationGenerated;
import org.semanticweb.elk.reasoner.saturation.inferences.PropagationInference;

class PropagationMatch1InferenceVisitor
		extends
			AbstractConclusionMatchInferenceVisitor<PropagationMatch1>
		implements
			PropagationInference.Visitor<Void> {

	PropagationMatch1InferenceVisitor(InferenceMatch.Factory factory,
			PropagationMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(PropagationGenerated inference) {
		factory.getPropagationGeneratedMatch1(inference, child);
		return null;
	}

}
