
package org.semanticweb.elk.reasoner.saturation.conclusions.model;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInference;

/**
 * A {@link ClassConclusion} that can be used in inferences that are assigned
 * with a {@link IndexedObjectProperty} sub-root
 * {@link SubClassConclusion#getSubDestination()} in addition to the
 * {@link IndexedClassExpression} root {@link ClassConclusion#getDestination()}
 * .
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface SubClassConclusion extends ClassConclusion {

	/**
	 * @return the {@link IndexedObjectProperty} associated with the inferences
	 *         with which this {@link SubClassConclusion} can be used. All
	 *         premises of such inferences must return the same {#getSubRoot()}
	 */
	public IndexedObjectProperty getSubDestination();

	/**
	 * 
	 * @return The {@link IndexedObjectProperty} that, together with
	 *         {@link ClassConclusion#getTraceRoot()}, identifies this
	 *         {@link SubClassConclusion} for tracing; every
	 *         {@link SubClassInference} that produces this
	 *         {@link SubClassConclusion} should be have the same value of
	 *         {@link SubClassInference#getTraceSubRoot()}
	 * 
	 * @see #getTraceRoot()
	 * @see SubClassInference#getTraceSubRoot()
	 */
	public IndexedObjectProperty getTraceSubRoot();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				BackwardLink.Factory,
				Propagation.Factory,
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
				BackwardLink.Visitor<O>,
				Propagation.Visitor<O>,
				SubContextInitialization.Visitor<O> {

		// combined interface

	}

}
