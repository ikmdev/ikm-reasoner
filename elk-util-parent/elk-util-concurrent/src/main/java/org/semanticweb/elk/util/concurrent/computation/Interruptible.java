
package org.semanticweb.elk.util.concurrent.computation;

/**
 * Instances of this interface may change the interruption status.
 * 
 * @author Peter Skocovsky
 */
public interface Interruptible {

	/**
	 * Change the interruption status to interrupted.
	 */
	void interrupt();

}
