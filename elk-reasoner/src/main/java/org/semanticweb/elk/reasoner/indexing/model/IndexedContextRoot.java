
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;



import org.semanticweb.elk.reasoner.saturation.ExtendedContext;

/**
 * An {@link IndexedObject} that can be used in special "root" positions of
 * other {@link IndexedObject}s. It corresponds to a {@link ElkClassExpression}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface IndexedContextRoot extends IndexedObject {

	/**
	 * @return The corresponding context or {@code null} if none was assigned.
	 */
	ExtendedContext getContext();

	/**
	 * Resets the corresponding context to {@code null}.
	 */
	void resetContext();

	/**
	 * Assign the given {@link ExtendedContext} to this {@link IndexedObject} if
	 * none was yet assigned.
	 * 
	 * @param context
	 *            the {@link ExtendedContext} which will be assigned to this
	 *            {@link IndexedObject}
	 * 
	 * @return the previously assigned {@link ExtendedContext} or {@code null}
	 *         if none was assigned (in which case the new
	 *         {@link ExtendedContext} will be assigned)
	 */
	ExtendedContext setContextIfAbsent(ExtendedContext context);

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
				IndexedClassExpression.Visitor<O>,
				IndexedRangeFiller.Visitor<O> {

		// combined interface

	}

	<O> O accept(IndexedContextRoot.Visitor<O> visitor);

}
