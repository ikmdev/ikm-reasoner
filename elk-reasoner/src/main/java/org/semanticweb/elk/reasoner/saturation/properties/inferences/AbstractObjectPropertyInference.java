
package org.semanticweb.elk.reasoner.saturation.properties.inferences;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;
import org.semanticweb.elk.reasoner.saturation.inferences.SaturationInference;
import org.semanticweb.elk.reasoner.tracing.AbstractTracingInference;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.TracingInference;

/**
 * A skeleton implementation of {@link ClassInference}
 * 
 * @author Yevgeny Kazakov
 *
 */
abstract class AbstractObjectPropertyInference extends AbstractTracingInference
		implements ObjectPropertyInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	abstract <F extends ObjectPropertyConclusion.Factory> Conclusion getConclusion(
			F factory);

//	@Override
//	public final Conclusion getConclusion(Conclusion.Factory factory) {
//		return getConclusion((ObjectPropertyConclusion.Factory) factory);
//	}

	@Override
	public final <O> O accept(TracingInference.Visitor<O> visitor) {
		return accept((ObjectPropertyInference.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(SaturationInference.Visitor<O> visitor) {
		return accept((ObjectPropertyInference.Visitor<O>) visitor);
	}

}
