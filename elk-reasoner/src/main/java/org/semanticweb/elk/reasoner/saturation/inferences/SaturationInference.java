
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.SaturationConclusion;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.ObjectPropertyInference;
import org.semanticweb.elk.reasoner.tracing.TracingInference;

/**
 * An {@link TracingInference} that produces {@link SaturationConclusion}s
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface SaturationInference extends TracingInference {

	public <O> O accept(Visitor<O> visitor);

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O>
			extends
				ClassInference.Visitor<O>,
				ObjectPropertyInference.Visitor<O> {

		// combined interface

	}

}
