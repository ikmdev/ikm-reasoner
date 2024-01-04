 
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch2;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch1;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubPropertyChainExpandedSubObjectPropertyOfMatch1;

class IndexedSubObjectPropertyOfAxiomMatch2InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedSubObjectPropertyOfAxiomMatch2>
		implements IndexedSubObjectPropertyOfAxiomMatch1Watch.Visitor<Void> {

	IndexedSubObjectPropertyOfAxiomMatch2InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedSubObjectPropertyOfAxiomMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(BackwardLinkCompositionMatch1 inferenceMatch1) {
		factory.getBackwardLinkCompositionMatch2(inferenceMatch1, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkReversedExpandedMatch1 inferenceMatch1) {
		factory.getBackwardLinkReversedExpandedMatch2(inferenceMatch1, child);
		return null;
	}

	@Override
	public Void visit(
			SubPropertyChainExpandedSubObjectPropertyOfMatch1 inferenceMatch1) {
		factory.getSubPropertyChainExpandedSubObjectPropertyOfMatch2(
				inferenceMatch1, child);
		return null;
	}

}