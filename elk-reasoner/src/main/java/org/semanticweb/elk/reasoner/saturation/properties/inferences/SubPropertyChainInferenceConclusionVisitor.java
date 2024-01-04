
package org.semanticweb.elk.reasoner.saturation.properties.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;
import org.semanticweb.elk.reasoner.tracing.ConclusionBaseFactory;

/**
 * A {@link SubPropertyChainInference.Visitor} that processes all conclusions of
 * the visited {@link SubPropertyChainInference}s using the provided
 * {@code SubPropertyChain.Visitor}.
 * 
 * @author Yevgeny Kazakov
 *
 * @param <O>
 */
public class SubPropertyChainInferenceConclusionVisitor<O>
		implements SubPropertyChainInference.Visitor<O> {

	private final ObjectPropertyConclusion.Factory conclusionFactory_;

	private final SubPropertyChain.Visitor<O> conclusionVisitor_;

	public SubPropertyChainInferenceConclusionVisitor(
			ObjectPropertyConclusion.Factory conclusionFactory,
			SubPropertyChain.Visitor<O> conclusionVisitor) {
		this.conclusionFactory_ = conclusionFactory;
		this.conclusionVisitor_ = conclusionVisitor;
	}

	public SubPropertyChainInferenceConclusionVisitor(
			SubPropertyChain.Visitor<O> conclusionVisitor) {
		this(new ConclusionBaseFactory(), conclusionVisitor);
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
