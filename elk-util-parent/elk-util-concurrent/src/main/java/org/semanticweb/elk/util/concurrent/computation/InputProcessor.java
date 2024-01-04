
package org.semanticweb.elk.util.concurrent.computation;

/**
 * A {@link Processor} that can be additionally used to submit jobs of a certain
 * type for concurrent processing. The jobs are submitted using
 * {@link #submit(Object)}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <J>
 *            the type of the jobs to be submitted and processed
 */
public interface InputProcessor<J> extends Processor {

	/**
	 * Submit a job to be processed by this {@link Processor}. This method can
	 * never fail or be interrupted. It is guaranteed that the submitted job is
	 * processed when all subsequent calls of {@link #process()} terminate.
	 * 
	 * @param job
	 *            the job to be submitted
	 */
	public void submit(J job);

}
