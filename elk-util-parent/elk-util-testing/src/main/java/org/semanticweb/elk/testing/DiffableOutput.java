
package org.semanticweb.elk.testing;



import org.semanticweb.elk.testing.DiffableOutput.Listener;

/**
 * Represents a reasoning output that can be compared with other outputs
 * 
 * @author Yevgeny Kazakov
 *
 * @param <E>
 *            the type of elementary elements of the output
 *
 * @param <O>
 *            the type of the output which elements can be compared
 * 
 */
public interface DiffableOutput<E, O> extends Diffable<O, Listener<E>> {

	public interface Listener<E> {
		void missing(E element);
	}

}
