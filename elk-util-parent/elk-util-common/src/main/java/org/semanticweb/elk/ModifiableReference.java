
package org.semanticweb.elk;



/**
 * A {@link Reference} whose value can be modified
 * 
 * @author Yevgeny Kazakov
 *
 * @param <O>
 */
public interface ModifiableReference<O> extends Reference<O> {

	/**
	 * Sets a reference to the given object; after this method returns,
	 * {@link #get()} should return this object
	 * 
	 * @param object
	 */
	void set(O object);

}
