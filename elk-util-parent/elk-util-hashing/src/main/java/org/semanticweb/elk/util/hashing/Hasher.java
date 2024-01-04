
package org.semanticweb.elk.util.hashing;

/**
 * An interface for defining custom hash codes,
 * 
 * @author Frantisek Simancik
 * @param <T>
 *            the type of objects for which the hash codes are defined
 * 
 */
public interface Hasher<T> {

	public int hash(T object);

}
