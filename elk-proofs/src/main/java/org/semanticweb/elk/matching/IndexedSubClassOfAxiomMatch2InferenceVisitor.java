
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedSubClassOfMatch1;

class IndexedSubClassOfAxiomMatch2InferenceVisitor
		extends
			AbstractConclusionMatchInferenceVisitor<IndexedSubClassOfAxiomMatch2>
		implements
			IndexedSubClassOfAxiomMatch1Watch.Visitor<Void> {

	IndexedSubClassOfAxiomMatch2InferenceVisitor(InferenceMatch.Factory factory,
			IndexedSubClassOfAxiomMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(
			SubClassInclusionExpandedSubClassOfMatch1 inferenceMatch1) {
		factory.getSubClassInclusionExpandedSubClassOfMatch2(inferenceMatch1,
				child);
		return null;
	}

}