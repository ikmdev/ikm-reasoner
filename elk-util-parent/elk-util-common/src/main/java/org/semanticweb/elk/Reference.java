
package org.semanticweb.elk;



/**
 * A reference to an object
 * 
 * @author Yevgeny Kazakov
 *
 * @param <O>
 *            the type of the value of this {@link Reference}
 */
public interface Reference<O> {

	/**
	 * @return the value of this {@link Reference}
	 */
	O get();

}
