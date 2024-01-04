
package org.semanticweb.elk.util.collections.chains;



/**
 * An object holding a (possibly {@code null}) reference to other object.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the object in the reference
 */
public interface Link<T> {

	/**
	 * @return the object assigned in the reference or {@code null} if there is
	 *         no object assigned
	 */
	T next();

}
