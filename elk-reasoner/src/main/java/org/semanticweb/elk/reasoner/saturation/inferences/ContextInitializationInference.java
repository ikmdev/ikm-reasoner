
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;



/**
 * A {@link ClassInference} producing a {@link ContextInitialization} conclusion
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ContextInitializationInference
		extends
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
				ContextInitializationNoPremises.Visitor<O> {

		// combined interface

	}

}
