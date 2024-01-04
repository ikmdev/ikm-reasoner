
package org.semanticweb.elk.util.concurrent.computation;



/**
 * An object that can be used for starting several copies of runnable tasks in
 * parallel, and waiting for its completion.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ConcurrentExecutor {

	/**
	 * Starts the given job concurrently on the given number of threads.
	 * 
	 * @param job
	 *            the job to run concurrently
	 * @param noInstances
	 *            the number of threads that should be used for running the job
	 * @return a {@link JobMonitor} using which one can detect if all running
	 *         copies of the job have terminated
	 * 
	 */
	JobMonitor submit(Runnable job, int noInstances);

}
