
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1Watch;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch5;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch2;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch4;
import org.semanticweb.elk.matching.inferences.InferenceMatch;

class ForwardLinkMatch2InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<ForwardLinkMatch2>
		implements ForwardLinkMatch1Watch.Visitor<Void> {

	ForwardLinkMatch2InferenceVisitor(InferenceMatch.Factory factory,
			ForwardLinkMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(BackwardLinkCompositionMatch5 inferenceMatch5) {
		factory.getBackwardLinkCompositionMatch6(inferenceMatch5, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkReversedExpandedMatch2 inferenceMatch2) {
		factory.getBackwardLinkReversedExpandedMatch3(inferenceMatch2, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkCompositionMatch4 inferenceMatch4) {
		factory.getForwardLinkCompositionMatch5(inferenceMatch4, child);
		return null;
	}

}
