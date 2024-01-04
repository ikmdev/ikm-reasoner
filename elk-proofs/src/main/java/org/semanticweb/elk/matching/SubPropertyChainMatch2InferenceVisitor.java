
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch2;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch3;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch4;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch2;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch3;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.PropagationGeneratedMatch2;
import org.semanticweb.elk.matching.inferences.PropertyRangeInheritedMatch2;
import org.semanticweb.elk.matching.inferences.SubPropertyChainExpandedSubObjectPropertyOfMatch2;

class SubPropertyChainMatch2InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<SubPropertyChainMatch2>
		implements SubPropertyChainMatch1Watch.Visitor<Void> {

	SubPropertyChainMatch2InferenceVisitor(InferenceMatch.Factory factory,
			SubPropertyChainMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(BackwardLinkCompositionMatch3 inferenceMatch3) {
		factory.getBackwardLinkCompositionMatch4(inferenceMatch3, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkCompositionMatch4 inferenceMatch4) {
		factory.getBackwardLinkCompositionMatch5(inferenceMatch4, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkCompositionMatch2 inferenceMatch2) {
		factory.getForwardLinkCompositionMatch3(inferenceMatch2, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkCompositionMatch3 inferenceMatch3) {
		factory.getForwardLinkCompositionMatch4(inferenceMatch3, child);
		return null;
	}

	@Override
	public Void visit(PropagationGeneratedMatch2 inferenceMatch2) {
		factory.getPropagationGeneratedMatch3(inferenceMatch2, child);
		return null;
	}

	@Override
	public Void visit(PropertyRangeInheritedMatch2 inferenceMatch2) {
		factory.getPropertyRangeInheritedMatch3(inferenceMatch2, child);
		return null;
	}

	@Override
	public Void visit(
			SubPropertyChainExpandedSubObjectPropertyOfMatch2 inferenceMatch2) {
		factory.getSubPropertyChainExpandedSubObjectPropertyOfMatch3(
				inferenceMatch2, child);
		return null;
	}

}