 
package org.semanticweb.elk.reasoner;

/**
 * A general class for jobs submitted for computation. Every job is initialized
 * with some input, and when the input is processed, this job can be used to
 * obtain the result of the computation.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <I>
 *            the type of the input of the job
 * @param <O>
 *            the type of the result of the computation
 */
public class ReasonerJob<I, O> {

	private final I input;

	private O output = null;

	/**
	 * Creating a job instance for the given input.
	 * 
	 * @param input
	 *            the input to be processed
	 */
	public ReasonerJob(I input) {
		this.input = input;
	}

	/**
	 * Returns the input for this job.
	 * 
	 * @return the input for this job
	 */
	public I getInput() {
		return this.input;
	}

	/**
	 * Returns the output of this job, or {@code null} if the job has not been
	 * processed yet.
	 * 
	 * @return the output of this job
	 */
	public O getOutput() {
		return this.output;
	}

	/**
	 * Set the output of this job to the given value.
	 * 
	 * @param output
	 *            the output of this job
	 */
	protected void setOutput(O output) {
		this.output = output;
	}

}
