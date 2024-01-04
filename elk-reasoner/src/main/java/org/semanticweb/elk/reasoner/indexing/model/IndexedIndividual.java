
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;

/**
 * An {@link IndexedClassExpression} that corresponds to an
 * {@link ElkIndividual}, which can be obtained by {@link #getElkEntity()}.
 * 
 * Notation:
 * 
 * <pre>
 * A
 * </pre>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 */
public interface IndexedIndividual extends IndexedClassEntity {

	/**
	 * @return The {@link ElkNamedIndividual} represented by this
	 *         {@link IndexedIndividual}.
	 */
	@Override
	ElkNamedIndividual getElkEntity();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedIndividual element);

	}

}
