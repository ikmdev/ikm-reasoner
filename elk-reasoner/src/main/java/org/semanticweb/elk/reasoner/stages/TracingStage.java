
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.reasoner.tracing.TraceState;
import org.semanticweb.elk.reasoner.tracing.TracingComputation;

/**
 * Executes {@link TracingComputation} to trace inferences queued in
 * {@link TraceState}
 * 
 * @author Peter Skocovsky
 */
public class TracingStage extends AbstractReasonerStage {

	private TracingComputation computation_ = null;

	public TracingStage(final AbstractReasonerState reasoner,
			final AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	public String getName() {
		return "Inference tracing";
	}

	@Override
	public void printInfo() {
		if (computation_ != null) {
			computation_.printStatistics();
		}
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute()) {
			return false;
		}
		computation_ = new TracingComputation(reasoner.getTraceState(),
				reasoner.getInterrupter(), reasoner.getProcessExecutor(),
				reasoner.getNumberOfWorkers(), reasoner.saturationState);
		return true;
	}

	@Override
	void executeStage() throws ElkException {
		computation_.process();
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute()) {
			return false;
		}
		this.computation_ = null;
		return true;
	}

}
