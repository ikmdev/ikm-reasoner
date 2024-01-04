
package org.semanticweb.elk.util.collections;



/**
 * A helper interface indicating that the class can provide direct access to its
 * elements to optimize some operations, such as random access and iteration.
 * Typically used with collections of elements. Used internally, e.g., to
 * optimize {@link LazySetIntersection}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <E>
 */
interface DirectAccess<E> {

	/**
	 * @return the array storing the elements of this object; some elements in
	 *         the array could be null
	 */
	E[] getRawData();

}
