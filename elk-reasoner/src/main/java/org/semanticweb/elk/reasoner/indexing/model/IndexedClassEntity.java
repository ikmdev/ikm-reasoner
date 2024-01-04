
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;

/**
 * An {@link IndexedClassExpression} that corresponds to an {@link ElkClass} or
 * an {@link ElkIndividual}, which can be obtained by {@link #getElkEntity()}.
 * 
 * Notation:
 * 
 * <pre>
 * A
 * </pre>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedClassEntity
		extends
			IndexedClassExpression,
			IndexedEntity {

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
				IndexedClass.Visitor<O>,
				IndexedIndividual.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
