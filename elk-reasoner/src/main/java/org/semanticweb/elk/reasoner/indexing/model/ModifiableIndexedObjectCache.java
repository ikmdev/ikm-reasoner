
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link IndexedObjectCache} elements of which can be added, removed, and
 * retrieved modulo structural equality.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedObjectCache extends IndexedObjectCache {

	/**
	 * 
	 * @param input
	 *            an {@link CachedIndexedObject}
	 * @return a structurally equal {@link CachedIndexedObject} of the same type
	 *         as the input containing in this {@link IndexedObjectCache} or
	 *         {@code null} if there is no such an object
	 */
	<T extends CachedIndexedObject<T>> T resolve(CachedIndexedObject<T> input);

	/**
	 * Adds a given {@link CachedIndexedObject} to this
	 * {@link IndexedObjectCache}; this method should be used only if no
	 * {@link CachedIndexedObject} that is structurally equal to the given one
	 * occurs in this {@link IndexedObjectCache}
	 * 
	 * @param input
	 *            the {@link CachedIndexedObject} to be added
	 */
	void add(CachedIndexedObject<?> input);

	/**
	 * Removes an object structurally equal to the given one from this
	 * {@link IndexedObjectCache}, if there is such an object
	 * 
	 * @param input
	 *            the {@link CachedIndexedObject} for which to remove the
	 *            structurally equal object
	 */
	void remove(CachedIndexedObject<?> input);

}
