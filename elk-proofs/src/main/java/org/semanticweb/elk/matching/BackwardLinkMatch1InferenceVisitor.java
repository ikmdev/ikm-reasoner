
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkComposition;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkInference;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkOfObjectHasSelf;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkOfObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkReversedExpanded;

class BackwardLinkMatch1InferenceVisitor
		extends
			AbstractConclusionMatchInferenceVisitor<BackwardLinkMatch1>
		implements
			BackwardLinkInference.Visitor<Void> {

	BackwardLinkMatch1InferenceVisitor(InferenceMatch.Factory factory,
			BackwardLinkMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(BackwardLinkComposition inference) {
		factory.getBackwardLinkCompositionMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkOfObjectHasSelf inference) {
		factory.getBackwardLinkOfObjectHasSelfMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkOfObjectSomeValuesFrom inference) {
		factory.getBackwardLinkOfObjectSomeValuesFromMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(BackwardLinkReversedExpanded inference) {
		factory.getBackwardLinkReversedExpandedMatch1(inference, child);
		return null;
	}

}
