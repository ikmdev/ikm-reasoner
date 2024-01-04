
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionDecomposedFirstConjunct;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionDecomposedInference;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionDecomposedSecondConjunct;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionExpandedDefinition;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionExpandedFirstEquivalentClass;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionExpandedSecondEquivalentClass;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionExpandedSubClassOf;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionObjectHasSelfPropertyRange;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionRange;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionTautology;

class SubClassInclusionDecomposedMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<SubClassInclusionDecomposedMatch1>
		implements SubClassInclusionDecomposedInference.Visitor<Void> {

	SubClassInclusionDecomposedMatch1InferenceVisitor(
			InferenceMatch.Factory factory,
			SubClassInclusionDecomposedMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(SubClassInclusionDecomposedFirstConjunct inference) {
		factory.getSubClassInclusionDecomposedFirstConjunctMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionDecomposedSecondConjunct inference) {
		factory.getSubClassInclusionDecomposedSecondConjunctMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionExpandedDefinition inference) {
		factory.getSubClassInclusionExpandedDefinitionMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionExpandedSubClassOf inference) {
		factory.getSubClassInclusionExpandedSubClassOfMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionObjectHasSelfPropertyRange inference) {
		factory.getSubClassInclusionObjectHasSelfPropertyRangeMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionRange inference) {
		factory.getSubClassInclusionRangeMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionTautology inference) {
		factory.getSubClassInclusionTautologyMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(SubClassInclusionExpandedFirstEquivalentClass inference) {
		factory.getSubClassInclusionExpandedFirstEquivalentClassMatch1(
				inference, child);
		return null;
	}

	@Override
	public Void visit(
			SubClassInclusionExpandedSecondEquivalentClass inference) {
		factory.getSubClassInclusionExpandedSecondEquivalentClassMatch1(
				inference, child);
		return null;
	}

}
