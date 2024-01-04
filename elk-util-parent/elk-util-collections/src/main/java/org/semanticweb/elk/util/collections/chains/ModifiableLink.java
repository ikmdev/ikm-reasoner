
package org.semanticweb.elk.util.collections.chains;

public interface ModifiableLink<T> {

	/**
	 * @return the linked object or {@code null} if there is no linked object
	 *         assigned
	 */
	T next();

	/**
	 * Setting the link to the given object. After that the {@link #next()}
	 * method should return this object.
	 * 
	 * @param next
	 *            the object to which the reference is set
	 */
	void setNext(T next);

}
