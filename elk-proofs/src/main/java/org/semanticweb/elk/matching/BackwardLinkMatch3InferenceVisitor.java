
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch3;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch6;
import org.semanticweb.elk.matching.inferences.BackwardLinkOfObjectHasSelfMatch2;
import org.semanticweb.elk.matching.inferences.BackwardLinkOfObjectSomeValuesFromMatch2;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch3;
import org.semanticweb.elk.matching.inferences.InferenceMatch;

class BackwardLinkMatch3InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<BackwardLinkMatch3>
		implements BackwardLinkMatch2Watch.Visitor<Void> {

	BackwardLinkMatch3InferenceVisitor(InferenceMatch.Factory factory,
			BackwardLinkMatch3 child) {
		super(factory, child);
	}

	@Override
	public Void visit(BackwardLinkCompositionMatch6 inferenceMatch6) {
		factory.getBackwardLinkCompositionMatch7(inferenceMatch6, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkOfObjectHasSelfMatch2 inferenceMatch2) {
		factory.getBackwardLinkOfObjectHasSelfMatch3(inferenceMatch2, child);
		return null;
	}

	@Override
	public Void visit(
			BackwardLinkOfObjectSomeValuesFromMatch2 inferenceMatch2) {
		factory.getBackwardLinkOfObjectSomeValuesFromMatch3(inferenceMatch2,
				child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkReversedExpandedMatch3 inferenceMatch3) {
		factory.getBackwardLinkReversedExpandedMatch4(inferenceMatch3, child);
		return null;
	}

}