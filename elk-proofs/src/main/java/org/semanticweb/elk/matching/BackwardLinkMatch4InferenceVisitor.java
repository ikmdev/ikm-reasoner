
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch3Watch;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch4;
import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch8;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyPropagatedMatch3;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch7;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectSomeValuesFromMatch3;

class BackwardLinkMatch4InferenceVisitor
		extends AbstractConclusionMatchInferenceVisitor<BackwardLinkMatch4>
		implements BackwardLinkMatch3Watch.Visitor<Void> {

	BackwardLinkMatch4InferenceVisitor(InferenceMatch.Factory factory,
			BackwardLinkMatch4 child) {
		super(factory, child);
	}

	@Override
	public Void visit(BackwardLinkCompositionMatch8 inferenceMatch8) {
		factory.getBackwardLinkCompositionMatch9(inferenceMatch8, child);
		return null;
	}

	@Override
	public Void visit(ClassInconsistencyPropagatedMatch3 inferenceMatch3) {
		factory.getClassInconsistencyPropagatedMatch4(inferenceMatch3, child);
		return null;
	}

	@Override
	public Void visit(ForwardLinkCompositionMatch7 inferenceMatch7) {
		factory.getForwardLinkCompositionMatch8(inferenceMatch7, child);
		return null;
	}

	@Override
	public Void visit(
			SubClassInclusionComposedObjectSomeValuesFromMatch3 inferenceMatch3) {
		factory.getSubClassInclusionComposedObjectSomeValuesFromMatch4(
				inferenceMatch3, child);
		return null;
	}

}