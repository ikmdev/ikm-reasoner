
package org.semanticweb.elk.util.collections.chains;

/**
 * A factoring for creating references
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of elements to which a reference should be created
 * @param <O>
 *            the type of the output references
 */
public interface ReferenceFactory<T, O> {

	/**
	 * Creates a references to the given object
	 * 
	 * @param object
	 * @return the reference to the given object
	 */
	O create(T object);

}
