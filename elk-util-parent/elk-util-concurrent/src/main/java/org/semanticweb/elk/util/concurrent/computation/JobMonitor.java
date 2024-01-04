
package org.semanticweb.elk.util.concurrent.computation;

/**
 * An object to monitor execution of a job.
 * 
 * @author Yevgeny Kazakov
 * 
 * @see ConcurrentExecutor
 *
 */
public interface JobMonitor {

	/**
	 * Blocks until the job is done
	 * 
	 * @throws InterruptedException
	 *             if was interrupted while waiting
	 */
	public void waitDone() throws InterruptedException;

}
