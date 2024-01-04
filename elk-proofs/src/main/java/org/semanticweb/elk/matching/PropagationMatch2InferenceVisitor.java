
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.PropagationMatch1Watch;
import org.semanticweb.elk.matching.conclusions.PropagationMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch.Factory;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectSomeValuesFromMatch2;

class PropagationMatch2InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<PropagationMatch2>
		implements PropagationMatch1Watch.Visitor<Void> {

	PropagationMatch2InferenceVisitor(Factory factory,
			PropagationMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(
			SubClassInclusionComposedObjectSomeValuesFromMatch2 inferenceMatch2) {
		factory.getSubClassInclusionComposedObjectSomeValuesFromMatch3(
				inferenceMatch2, child);
		return null;
	}

}
