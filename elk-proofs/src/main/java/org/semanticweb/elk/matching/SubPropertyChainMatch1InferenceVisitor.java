
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.SubPropertyChainExpandedSubObjectPropertyOf;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.SubPropertyChainInference;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.SubPropertyChainTautology;

class SubPropertyChainMatch1InferenceVisitor
		extends
			AbstractConclusionMatchInferenceVisitor<SubPropertyChainMatch1>
		implements
			SubPropertyChainInference.Visitor<Void> {

	SubPropertyChainMatch1InferenceVisitor(InferenceMatch.Factory factory,
			SubPropertyChainMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(SubPropertyChainExpandedSubObjectPropertyOf inference) {
		factory.getSubPropertyChainExpandedSubObjectPropertyOfMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(SubPropertyChainTautology inference) {
		factory.getSubPropertyChainTautologyMatch1(inference, child);
		return null;
	}

}
