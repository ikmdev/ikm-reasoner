
package org.semanticweb.elk.reasoner.saturation.conclusions.model;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;

/**
 * A special {@link ClassConclusion} that is used to initialize inferences for
 * {@link ClassConclusion}s associated with the concept represented by
 * {@link #getDestination()}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * ![C]
 * </pre>
 * 
 * The axiom has no logical meaning (equivalent to a tautology)<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ContextInitialization
		extends
			ClassConclusion,
			InitializationConclusion {

	public static final String NAME = "Context Initialization";

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ContextInitialization getContextInitialization(IndexedContextRoot root);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		public O visit(ContextInitialization conclusion);

	}

}
