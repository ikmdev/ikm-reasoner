
package org.semanticweb.elk.util.concurrent.computation;

/**
 * An factory for creation of {@link InputProcessor}s of a given type. This
 * factory is intended to be used in {@link ConcurrentComputationWithInputs} to process
 * the input concurrently by independent workers. In this case, an
 * {@link InputProcessor} will be created for each worker. Each
 * {@link InputProcessor} created by this {@link InputProcessorFactory} should
 * be used from at most one thread since it may contain some non-thread-safe
 * worker-local objects.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the {@link Processor}s produced by this
 *            {@link ProcessorFactory}
 */
public interface ProcessorFactory<P extends Processor>
		extends InterruptMonitor {

	/**
	 * @return a new {@link Processor} of the given type
	 */
	public P getEngine();

	/**
	 * a hook function to be called when all jobs are processed
	 */
	public void finish();

}
