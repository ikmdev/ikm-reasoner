
package org.semanticweb.elk.util.concurrent.computation;

/**
 * An abstract interface for concurrent processing. Processing is performed by
 * calling the method {@link #process()}. It is assumed that each
 * {@link Processor} can be used only within one thread, but several different
 * {@link Processor}s can work concurrently. In this case, processing is
 * finished when all of the concurrent {@link Processor}s are finished.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface Processor {

	/**
	 * Performs processing using this {@link Processor}. For each
	 * {@link Processor} object the method should be called only from one
	 * thread, but several {@link Processor} objects can call this method
	 * concurrently. In this case, processing performs concurrently and is
	 * finished when all concurrent calls of {@link #process()} are finished.
	 * 
	 * @throws InterruptedException
	 *             if interrupted during processing
	 */
	public void process() throws InterruptedException;

	/**
	 * Indicate that processing of by this object is finished. This method
	 * should be eventually called after every call of {@link #process()}. But
	 * it is not necessary that every call of {@link #process()} should be
	 * followed by {@link #finish()}. E.g., it can be followed by
	 * {@link #process()} when it is determined that processing is not yet
	 * finished (e.g., due to concurrent computation).
	 */
	public void finish();

}
