
package org.semanticweb.elk.util.concurrent.computation;



import java.util.concurrent.TimeUnit;

/**
 * A collection of convenience methods for creating {@link ConcurrentExecutor}s
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ConcurrentExecutors {

	private static ConcurrentExecutor DEFAULT_ = create("elk-worker");

	/**
	 * Creates a {@link ConcurrentExecutor} with the given name (used as a
	 * prefix for creating thread), and given timeout for running threads. If a
	 * thread did not process a job within the given timeout, the thread is
	 * terminated.
	 * 
	 * @param name
	 * @param timeout
	 * @param unit
	 * @return the {@link ConcurrentExecutor} associated with the given
	 *         parameters
	 */
	public static ConcurrentExecutor create(String name, long timeout,
			TimeUnit unit) {
		return new ConcurrentExecutorImpl(name, timeout, unit);
	}

	/**
	 * Creates a {@link ConcurrentExecutor} with the given name (used as a
	 * prefix for creating thread), and the timeout of 1 second for running
	 * threads. If a thread did not process a job within the given timeout, the
	 * thread is terminated.
	 * 
	 * @param name
	 * @return the {@link ConcurrentExecutor} associated with the given
	 *         parameters
	 */
	public static ConcurrentExecutor create(String name) {
		return create(name, 1L, TimeUnit.SECONDS);
	}

	/**
	 * @return the default {@link ConcurrentExecutor} with the timeout of 1
	 *         second for running threads.
	 */
	public static ConcurrentExecutor getDefault() {
		return DEFAULT_;
	}

}
