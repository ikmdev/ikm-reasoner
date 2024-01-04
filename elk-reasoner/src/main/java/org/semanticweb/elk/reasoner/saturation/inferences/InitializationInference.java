
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.InitializationConclusion;

/**
 * A {@link ClassInference} that produce an {@link InitializationConclusion}
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface InitializationInference extends ClassInference {

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
				ContextInitializationInference.Visitor<O>,
				SubContextInitializationInference.Visitor<O> {

		// combined interface

	}

}
