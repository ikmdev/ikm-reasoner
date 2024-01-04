
package org.semanticweb.elk.reasoner.saturation.conclusions.model;

import org.semanticweb.elk.reasoner.saturation.inferences.SaturationInference;
import org.semanticweb.elk.reasoner.tracing.Conclusion;



/**
 * A {@link Conclusion} that represents an axiom derived by saturation inference
 * rules.
 * 
 * @see SaturationInference
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface SaturationConclusion extends Conclusion {

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ClassConclusion.Factory,
				ObjectPropertyConclusion.Factory {

		// combined interface

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends
				ClassConclusion.Visitor<O>,
				ObjectPropertyConclusion.Visitor<O> {

		// combined interface

	}

}
