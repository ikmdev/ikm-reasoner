
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;



/**
 * A {@link SubClassInference} that produces an {@link SubContextInitialization}
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface SubContextInitializationInference
		extends
			SubClassInference,
			InitializationInference {

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
				SubContextInitializationNoPremises.Visitor<O> {

		// combined interface

	}

}
