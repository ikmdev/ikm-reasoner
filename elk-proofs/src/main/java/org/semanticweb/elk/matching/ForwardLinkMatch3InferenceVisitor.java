 
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch2Watch;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch3;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch5;
import org.semanticweb.elk.matching.inferences.ForwardLinkOfObjectHasSelfMatch2;
import org.semanticweb.elk.matching.inferences.ForwardLinkOfObjectSomeValuesFromMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;

class ForwardLinkMatch3InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<ForwardLinkMatch3>
		implements ForwardLinkMatch2Watch.Visitor<Void> {

	ForwardLinkMatch3InferenceVisitor(InferenceMatch.Factory factory,
			ForwardLinkMatch3 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ForwardLinkCompositionMatch5 inferenceMatch5) {
		factory.getForwardLinkCompositionMatch6(inferenceMatch5, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkOfObjectHasSelfMatch2 inferenceMatch2) {
		factory.getForwardLinkOfObjectHasSelfMatch3(inferenceMatch2, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkOfObjectSomeValuesFromMatch2 inferenceMatch2) {
		factory.getForwardLinkOfObjectSomeValuesFromMatch3(inferenceMatch2,
				child);
		return null;
	}

}
