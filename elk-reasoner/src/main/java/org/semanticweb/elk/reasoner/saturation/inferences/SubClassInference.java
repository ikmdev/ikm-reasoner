 
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link ClassInference} that produces {@link SubClassConclusion}s
 * 
 * @author Yevgeny Kazakov
 */
public interface SubClassInference extends ClassInference {

	/**
	 * @return the {@link IndexedObjectProperty} of the {@link Context} in which
	 *         the conclusion of this {@link SubClassInference} should
	 *         participate in inferences. It cannot be {@code null}.
	 */
	public IndexedObjectProperty getSubDestination();

	/**
	 * @return the {@link IndexedObjectProperty}, which is the same as
	 *         {@link SubClassConclusion#getTraceSubRoot()} for the conclusion
	 *         of this {@link SubClassInference} and for some of the premises of
	 *         this {@link SubClassInference}, if it has any (i.e., it is not an
	 *         {@link SubContextInitializationInference}). This value is used
	 *         for tracing of inferences. It cannot be {@code null}.
	 * 
	 * @see ClassConclusion#getTraceRoot()
	 */
	public IndexedObjectProperty getTraceSubRoot();

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
				BackwardLinkInference.Visitor<O>,
				PropagationInference.Visitor<O>,
				SubContextInitializationInference.Visitor<O> {

		// combined interface

	}

}
