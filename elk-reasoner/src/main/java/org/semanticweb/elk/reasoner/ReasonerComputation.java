
package org.semanticweb.elk.reasoner;

import org.semanticweb.elk.exceptions.ElkRuntimeException;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentComputation;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;
import org.semanticweb.elk.util.concurrent.computation.ProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link ConcurrentComputation} used for executing of reasoner stages
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <F>
 *            the type of the factory for the input processors
 */
public class ReasonerComputation<F extends ProcessorFactory<?>> extends
		ConcurrentComputation<F> {

	// logger for this class
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(ReasonerComputation.class);

	public ReasonerComputation(F inputProcessorFactory,
			ConcurrentExecutor executor, int maxWorkers) {
		super(inputProcessorFactory, executor, maxWorkers);
	}

	/**
	 * Process the given input concurrently using the provided input processor.
	 * If the process has been interrupted, this method can be called again to
	 * continue the computation.
	 */
	public void process() {

		if (!start()) {
			String message = "Could not start workers required for reasoner computation!";
			LOGGER_.error(message);
			throw new ElkRuntimeException(message);
		}

		try {
			finish();
		} catch (InterruptedException e) {
			// restore interrupt status
			Thread.currentThread().interrupt();
			throw new ElkRuntimeException(
					"Reasoner computation interrupted externally!");
		}
	}

}
