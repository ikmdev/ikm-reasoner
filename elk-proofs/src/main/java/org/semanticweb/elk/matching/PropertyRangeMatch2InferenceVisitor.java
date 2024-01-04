
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch1Watch;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionObjectHasSelfPropertyRangeMatch2;
import org.semanticweb.elk.matching.inferences.SubClassInclusionRangeMatch1;

class PropertyRangeMatch2InferenceVisitor
		extends
			AbstractConclusionMatchInferenceVisitor<PropertyRangeMatch2>
		implements
			PropertyRangeMatch1Watch.Visitor<Void> {

	PropertyRangeMatch2InferenceVisitor(InferenceMatch.Factory factory,
			PropertyRangeMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(
			SubClassInclusionObjectHasSelfPropertyRangeMatch2 inferenceMatch2) {
		factory.getSubClassInclusionObjectHasSelfPropertyRangeMatch3(
				inferenceMatch2, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionRangeMatch1 inferenceMatch1) {
		factory.getSubClassInclusionRangeMatch2(inferenceMatch1, child);
		return null;
	}

}