
package org.semanticweb.elk.reasoner.saturation.properties.inferences;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;

public class ObjectPropertyInferenceConclusionVisitor<O>
		implements ObjectPropertyInference.Visitor<O> {

	private final ObjectPropertyConclusion.Factory conclusionFactory_;

	private final ObjectPropertyConclusion.Visitor<O> conclusionVisitor_;

	public ObjectPropertyInferenceConclusionVisitor(
			ObjectPropertyConclusion.Factory conclusionFactory,
			ObjectPropertyConclusion.Visitor<O> conclusionVisitor) {
		this.conclusionFactory_ = conclusionFactory;
		this.conclusionVisitor_ = conclusionVisitor;
	}

	@Override
	public O visit(PropertyRangeInherited inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(SubPropertyChainExpandedSubObjectPropertyOf inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(SubPropertyChainTautology inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

}
