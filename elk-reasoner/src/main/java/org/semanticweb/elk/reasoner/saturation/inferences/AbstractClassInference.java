
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.tracing.AbstractTracingInference;
import org.semanticweb.elk.reasoner.tracing.TracingInference;

/**
 * A skeleton implementation of {@link ClassInference}
 * 
 * @author Yevgeny Kazakov
 *
 */
abstract class AbstractClassInference extends AbstractTracingInference
		implements ClassInference {

	private final IndexedContextRoot destination_;

	protected AbstractClassInference(IndexedContextRoot destination) {
		this.destination_ = destination;
	}

	@Override
	public final IndexedContextRoot getDestination() {
		return this.destination_;
	}

	@Override
	public IndexedContextRoot getTraceRoot() {
		return this.destination_;
	}

	@Override
	public final <O> O accept(TracingInference.Visitor<O> visitor) {
		return accept((ClassInference.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(SaturationInference.Visitor<O> visitor) {
		return accept((ClassInference.Visitor<O>) visitor);
	}

}
