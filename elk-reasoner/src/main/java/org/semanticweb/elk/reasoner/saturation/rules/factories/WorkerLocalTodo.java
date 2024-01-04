
package org.semanticweb.elk.reasoner.saturation.rules.factories;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;

/**
 * A queue to keep {@link ClassInference}s that should be processed in the
 * {@link Context} currently processed by the worker. It is not thread-safe, so
 * all methods should be accessed from the same thread.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface WorkerLocalTodo {

	/**
	 * @return the next {@link ClassInference} in the queue or {@link null} if there
	 *         is no such {@link ClassInference}
	 */
	ClassInference poll();

	/**
	 * Inserts the given {@link ClassInference} to be processed in the current
	 * {@link Context}
	 * 
	 * @param inference
	 */
	void add(ClassInference inference);

	/**
	 * @return {@code true} if this {@link WorkerLocalTodo} is assigned for
	 *         processing some {@link Context} or {@code false} otherwise. The
	 *         root of this {@link Context} can be obtained by
	 *         {@link #getActiveRoot()}
	 */
	boolean isActivated();

	/**
	 * @return the {@link IndexedContextRoot} of the currently assigned
	 *         {@link Context} or {@code null} if this {@link WorkerLocalTodo}
	 *         is not activated.
	 * @see #isActivated()
	 * @see Context#getRoot()
	 */
	IndexedContextRoot getActiveRoot();

	/**
	 * Set the new value of the root for the currently processed {@link Context}
	 * . This will activate this {@link WorkerLocalTodo} (
	 * {@link #getActiveRoot()} will return this value).
	 * 
	 * @param currentActiveRoot
	 *            the new value of the root for the currently processed
	 *            {@link Context}
	 */
	void setActiveRoot(IndexedContextRoot currentActiveRoot);

	/**
	 * Deactivates this {@link WorkerLocalTodo}. After that,
	 * {@link #isActivated()} returns {@code false}
	 * 
	 * @return {@code true} if this {@link WorkerLocalTodo} was activated and
	 *         {@code false} otherwise
	 */
	boolean deactivate();
}
