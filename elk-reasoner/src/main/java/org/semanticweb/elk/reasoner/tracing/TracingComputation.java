
package org.semanticweb.elk.reasoner.tracing;

import org.semanticweb.elk.reasoner.ReasonerComputation;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationStatistics;
import org.semanticweb.elk.reasoner.tracing.factories.SingleContextTracingFactory;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A reasoner computation for single context tracing. Uses
 * {@link SingleContextTracingFactory} to perform the computation.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author Yevgeny Kazakov
 * @author Peter Skocovsky
 */
public class TracingComputation
		extends ReasonerComputation<SingleContextTracingFactory> {

	public TracingComputation(final TraceState tracingState,
			final InterruptMonitor interrupter,
			final ConcurrentExecutor executor, final int maxWorkers,
			final SaturationState<?> saturationState) {
		super(new SingleContextTracingFactory(tracingState, interrupter,
				saturationState, maxWorkers), executor, maxWorkers);
	}

	public void printStatistics() {
		processorFactory.printStatistics();
	}

	public SaturationStatistics getRuleAndConclusionStatistics() {
		return processorFactory.getRuleAndConclusionStatistics();
	}

}
