
package org.semanticweb.elk.reasoner.tracing;



/**
 * Represents the conclusion of the given {@link TracingInference}
 * 
 * @author Yevgeny Kazakov
 *
 */
class TracingInferenceConclusion extends AbstractConclusion {

	private final TracingInference inference_;

	TracingInferenceConclusion(TracingInference inference) {
		this.inference_ = inference;
	}

	@Override
	public <O> O accept(Conclusion.Visitor<O> visitor) {
		return inference_.accept(new TracingInferenceConclusionVisitor<O>(visitor));
	}

}