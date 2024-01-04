
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.PropertyRangeInference;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.PropertyRangeInherited;

class PropertyRangeMatch1InferenceVisitor
		extends
			AbstractConclusionMatchInferenceVisitor<PropertyRangeMatch1>
		implements
			PropertyRangeInference.Visitor<Void> {

	PropertyRangeMatch1InferenceVisitor(InferenceMatch.Factory factory,
			PropertyRangeMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(PropertyRangeInherited inference) {
		factory.getPropertyRangeInheritedMatch1(inference, child);
		return null;
	}

}
