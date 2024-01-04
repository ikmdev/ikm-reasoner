
package org.semanticweb.elk.util.collections.chains;



/**
 * A linked list of elements together with helper functions to find, create if
 * not found, and remove elements in the chain.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the types of the elements in the chain
 * @see Matcher
 */
public interface Chain<T extends ModifiableLink<T>> extends ModifiableLink<T> {

	/**
	 * Finds the first element in the chain that satisfies the provided
	 * {@link Matcher}. This function does not modify the chain. If the chain is
	 * modified during calling of this function, the behavior of the function is
	 * not specified.
	 * 
	 * @param matcher
	 *            the object describing an element to search for
	 * @return the object contained in the chain that satisfies the provided
	 *         {@link Matcher} or {@code null} if no such element is found
	 */
	public <S extends T> S find(Matcher<? super T, S> matcher);

	/**
	 * Finds an element in the chain satisfies the provided {@link Matcher}, or
	 * if no such element is found, creates a new element using the provided
	 * {@link ReferenceFactory} and inserts it into the chain. In the letter
	 * case, the chain is modified.
	 * 
	 * @param matcher
	 *            the object describing the element to search for
	 * @param factory
	 *            the factory for creating references
	 * @return the object that satisfies the provided {@link Matcher} if found
	 *         in the chain, or the newly created and inserted element object
	 *         otherwise
	 */
	public <S extends T> S getCreate(Matcher<? super T, S> matcher,
			ReferenceFactory<T, S> factory);

	/**
	 * Removes the first element in the chain that satisfies the provided
	 * {@link Matcher}. If such element is found, the chain is modified.
	 * 
	 * @param descriptor
	 *            the object describing the element to search for
	 * @return the removed element, if found, or {@code null} if not found
	 */
	public <S extends T> S remove(Matcher<? super T, S> descriptor);

}
