
package org.semanticweb.elk.reasoner.saturation.conclusions.model;



import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.SubContext;

/**
 * A {@link ClassConclusion} used to initialized {@link Context}s and
 * {@link SubContext}s
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface InitializationConclusion extends ClassConclusion {

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ContextInitialization.Factory,
				SubContextInitialization.Factory {

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
				ContextInitialization.Visitor<O>,
				SubContextInitialization.Visitor<O> {

		// combined interface

	}

}
