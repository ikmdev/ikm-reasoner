
package org.semanticweb.elk.reasoner.taxonomy.model;

/**
 * Instances of this interface are able to return keys for objects of type
 * <code>T</code>. The purpose of these keys is that the methods
 * {@link #hashCode()} and {@link #equals(Object)} are called on them instead of
 * the original objects.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of the objects for which the keys are provided.
 */
public interface KeyProvider<T> {

	/**
	 * Returns the key for <code>arg</code>. The methods {@link #hashCode()} and
	 * {@link #equals(Object)} will be called on this key instead of the
	 * original object <code>arg</code>.
	 * 
	 * @param arg
	 *            The object for which the key should be returned.
	 * @return the key for <code>arg</code>.
	 */
	Object getKey(T arg);

}
