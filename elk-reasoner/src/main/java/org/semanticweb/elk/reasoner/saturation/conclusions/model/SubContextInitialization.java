
package org.semanticweb.elk.reasoner.saturation.conclusions.model;


import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;

/**
 * A special {@link ClassConclusion} that is used to initialize inferences for
 * {@link ClassConclusion}s associated with the concept expression represented
 * by {@link #getDestination()} and object property expression represented by
 * {@link #getSubDestination()}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * ![C:R]
 * </pre>
 * 
 * The axiom has no logical meaning (equivalent to a tautology)<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * R = {@link #getSubDestination()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface SubContextInitialization
		extends
			SubClassConclusion,
			InitializationConclusion {

	public static final String NAME = "Sub-Context Initialization";

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		SubContextInitialization getSubContextInitialization(
				IndexedContextRoot root, IndexedObjectProperty subRoot);

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

		public O visit(SubContextInitialization conclusion);

	}

}
