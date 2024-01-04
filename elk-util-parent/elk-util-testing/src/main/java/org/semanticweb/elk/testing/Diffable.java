
package org.semanticweb.elk.testing;



/**
 * Represents an output that can be compared with other outputs
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <O>
 *            the type of the output which elements can be compared
 * @param <L>
 *            the listener using which one can report missing elements
 * 
 */
public interface Diffable<O, L> {

	boolean containsAllElementsOf(O other);

	void reportMissingElementsOf(O other, L listener);

}
