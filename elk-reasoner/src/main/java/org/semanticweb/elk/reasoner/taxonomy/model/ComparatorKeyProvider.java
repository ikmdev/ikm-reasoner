
package org.semanticweb.elk.reasoner.taxonomy.model;

import java.util.Comparator;

/**
 * A {@link KeyProvider} that provides also a {@link Comparator} for objects of
 * type <code>T</code>.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of the objects for which the comparator should be
 *            provided.
 */
public interface ComparatorKeyProvider<T> extends KeyProvider<T> {

	/**
	 * Returns the comparator that should be used for comparison of the objects
	 * of type <code>T</code>. This comparator <strong>must</strong> be
	 * consistent with {@link #getKey(Object) getKey(Object).equals(Object)}!
	 * 
	 * @return the comparator that should be used for comparison of the objects
	 *         of type <code>T</code>.
	 */
	Comparator<? super T> getComparator();

}
