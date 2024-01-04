
package org.semanticweb.elk.util.concurrent.computation;

/**
 * A listener to be used with {@link InputProcessor} that can be used to perform
 * actions when a job is processed.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <J>
 *            the type of the jobs used in the job processor
 */
public interface InputProcessorListenerNotifyFinishedJob<J> {

	/**
	 * This function is called after the input processor detects when the job is
	 * fully processed. When the submitted job is processed, this method will be
	 * guaranteed to be called with this job as an argument. If
	 * {@link InputProcessor#submit(Object)} is called followed with
	 * {@link InputProcessor#process()}, it is guaranteed that
	 * {@link #notifyFinished(Object)} will be called (perhaps from some other
	 * thread) before no instance of {@link InputProcessor#process()} is
	 * running.
	 * 
	 * @param job
	 *            the job that has been processed
	 * @throws InterruptedException
	 *             if interrupted during the notification
	 */
	public void notifyFinished(J job) throws InterruptedException;

}
