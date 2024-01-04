
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedDefinedClass;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedEntity;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedInference;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedObjectUnionOf;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionOwlThing;

class SubClassInclusionComposedMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<SubClassInclusionComposedMatch1>
		implements SubClassInclusionComposedInference.Visitor<Void> {

	SubClassInclusionComposedMatch1InferenceVisitor(
			InferenceMatch.Factory factory,
			SubClassInclusionComposedMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(SubClassInclusionComposedDefinedClass inference) {
		factory.getSubClassInclusionComposedDefinedClassMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionComposedEntity inference) {
		factory.getSubClassInclusionComposedEntityMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionComposedObjectIntersectionOf inference) {
		factory.getSubClassInclusionComposedObjectIntersectionOfMatch1(
				inference, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionComposedObjectSomeValuesFrom inference) {
		factory.getSubClassInclusionComposedObjectSomeValuesFromMatch1(
				inference, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionComposedObjectUnionOf inference) {
		factory.getSubClassInclusionComposedObjectUnionOfMatch1(inference,
				child);
		return null;
	}
	
	@Override
	public Void visit(SubClassInclusionOwlThing inference) {
		factory.getSubClassInclusionOwlThingMatch1(inference, child);
		return null;
	}


}
