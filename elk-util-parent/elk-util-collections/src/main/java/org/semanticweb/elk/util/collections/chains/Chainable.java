
package org.semanticweb.elk.util.collections.chains;

/**
 * Elements that can be inserted and removed from {@link Chain}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the types of the elements in the chain
 */
public interface Chainable<T extends ModifiableLink<T>> extends
		ModifiableLink<T> {

	/**
	 * Adds this element to the given {@link Chain}
	 * 
	 * @param chain
	 * @return {@code true} if the operation was successful and {@code false}
	 *         otherwise; if {@code false} is returned, this {@link Chain}
	 *         remains unchanged
	 */
	public boolean addTo(Chain<T> chain);

	/**
	 * Removes this element from the given {@link Chain}
	 * 
	 * @param chain
	 *            to be removed
	 * @return {@code true} if the operation was successful and {@code false}
	 *         otherwise; if {@code false} is returned, this {@link Chain}
	 *         remains unchanged
	 */
	public boolean removeFrom(Chain<T> chain);

}
