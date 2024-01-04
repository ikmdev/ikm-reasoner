
package org.semanticweb.elk.util.collections.entryset;

/**
 * A common interface for implementing entries of an {@link EntryCollection}.
 * Entries should be connected to each other, so basic operations include
 * setting and getting a reference to the next element.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of objects this object can be structurally equal to
 * 
 * @param <N>
 *            the type of the next linked element
 */
public interface Entry<T extends Entry<T, N>, N> {

	/**
	 * Setting the input element as the next element of the entry.
	 * 
	 * @param next
	 *            the object that should be set as the next element of the
	 *            record
	 */
	void setNext(N next);

	/**
	 * Returns the next next element of the entry, or null if there is no next
	 * element
	 * 
	 * @return the next element of the entry
	 */
	N getNext();

	T structuralEquals(Object other);

	int structuralHashCode();

}
