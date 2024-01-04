
package org.semanticweb.elk;

/**
 * This interface represents a lock that may be locked multiple times
 * (internally). It becomes unlocked only if it is unlocked the same number of
 * times.
 * 
 * @author Peter Skocovsky
 */
public interface Lock {

	/**
	 * Decrements the number of times this lock is locked. It becomes unlocked
	 * when this method is called at least as many times the lock is locked.
	 * 
	 * @return {@code true} if the lock was unlocked by this call, {@code false}
	 *         otherwise.
	 */
	boolean unlock();

}
