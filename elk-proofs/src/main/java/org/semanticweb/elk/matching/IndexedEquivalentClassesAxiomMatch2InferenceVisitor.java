
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedDefinedClassMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedDefinitionMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedFirstEquivalentClassMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedSecondEquivalentClassMatch1;

class IndexedEquivalentClassesAxiomMatch2InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedEquivalentClassesAxiomMatch2>
		implements IndexedEquivalentClassesAxiomMatch1Watch.Visitor<Void> {

	IndexedEquivalentClassesAxiomMatch2InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedEquivalentClassesAxiomMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(
			SubClassInclusionComposedDefinedClassMatch1 inferenceMatch1) {
		factory.getSubClassInclusionComposedDefinedClassMatch2(inferenceMatch1,
				child);
		return null;
	}

	@Override
	public Void visit(
			SubClassInclusionExpandedDefinitionMatch1 inferenceMatch1) {
		factory.getSubClassInclusionExpandedDefinitionMatch2(inferenceMatch1,
				child);
		return null;
	}

	@Override
	public Void visit(
			SubClassInclusionExpandedFirstEquivalentClassMatch1 inferenceMatch1) {
		factory.getSubClassInclusionExpandedFirstEquivalentClassMatch2(
				inferenceMatch1, child);
		return null;
	}

	@Override
	public Void visit(
			SubClassInclusionExpandedSecondEquivalentClassMatch1 inferenceMatch1) {
		factory.getSubClassInclusionExpandedSecondEquivalentClassMatch2(
				inferenceMatch1, child);
		return null;
	}

}