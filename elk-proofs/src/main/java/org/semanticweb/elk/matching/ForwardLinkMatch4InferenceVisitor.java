
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3Watch;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch4;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch7;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch4;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch6;
import org.semanticweb.elk.matching.inferences.InferenceMatch;

class ForwardLinkMatch4InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<ForwardLinkMatch4>
		implements ForwardLinkMatch3Watch.Visitor<Void> {

	ForwardLinkMatch4InferenceVisitor(InferenceMatch.Factory factory,
			ForwardLinkMatch4 child) {
		super(factory, child);
	}

	@Override
	public Void visit(BackwardLinkCompositionMatch7 inferenceMatch7) {
		factory.getBackwardLinkCompositionMatch8(inferenceMatch7, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkReversedExpandedMatch4 inferenceMatch4) {
		factory.getBackwardLinkReversedExpandedMatch5(inferenceMatch4, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkCompositionMatch6 inferenceMatch6) {
		factory.getForwardLinkCompositionMatch7(inferenceMatch6, child);
		return null;
	}

}
