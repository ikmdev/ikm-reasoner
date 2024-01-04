
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.ForwardLinkComposition;
import org.semanticweb.elk.reasoner.saturation.inferences.ForwardLinkInference;
import org.semanticweb.elk.reasoner.saturation.inferences.ForwardLinkOfObjectHasSelf;
import org.semanticweb.elk.reasoner.saturation.inferences.ForwardLinkOfObjectSomeValuesFrom;

class ForwardLinkMatch1InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<ForwardLinkMatch1>
		implements ForwardLinkInference.Visitor<Void> {

	ForwardLinkMatch1InferenceVisitor(InferenceMatch.Factory factory,
			ForwardLinkMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ForwardLinkComposition inference) {
		factory.getForwardLinkCompositionMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkOfObjectHasSelf inference) {
		factory.getForwardLinkOfObjectHasSelfMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkOfObjectSomeValuesFrom inference) {
		factory.getForwardLinkOfObjectSomeValuesFromMatch1(inference, child);
		return null;
	}

}
