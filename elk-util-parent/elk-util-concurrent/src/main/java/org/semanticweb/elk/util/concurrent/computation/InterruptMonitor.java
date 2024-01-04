
package org.semanticweb.elk.util.concurrent.computation;

/**
 * Instances of this interface may monitor the interruption status.
 * 
 * @author Peter Skocovsky
 */
public interface InterruptMonitor {

	/**
	 * @return {@code true} after this monitor was notified about interruption,
	 *         {@code false} if it was not.
	 */
	boolean isInterrupted();

}
