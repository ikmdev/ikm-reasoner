 
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.reasoner.query.ClassExpressionQueryComputation;

/**
 * Stage that computes direct super-, direct sub-, and equivalent classes of the
 * queried class expressions.
 * 
 * @author Peter Skocovsky
 */
class ClassExpressionQueryStage extends AbstractReasonerStage {

	/**
	 * the computation used for this stage
	 */
	protected ClassExpressionQueryComputation computation_ = null;

	public ClassExpressionQueryStage(final AbstractReasonerState reasoner,
			final AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	public String getName() {
		return "Class Expression Query Computation";
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute()) {
			return false;
		}

		this.computation_ = new ClassExpressionQueryComputation(
				reasoner.classExpressionQueryState
						.getNotSaturatedQueriedClassExpressions(),
				reasoner.getInterrupter(), reasoner.getProcessExecutor(),
				workerNo, reasoner.getProgressMonitor(),
				reasoner.saturationState, reasoner.classExpressionQueryState
						.getTransitiveReductionOutputProcessor());

		return true;
	}

	@Override
	public void executeStage() throws ElkInterruptedException {
		computation_.process();
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute()) {
			return false;
		}
		reasoner.ruleAndConclusionStats
				.add(computation_.getRuleAndConclusionStatistics());
		this.computation_ = null;
		return true;
	}

	@Override
	public void printInfo() {
		if (computation_ != null) {
			computation_.printStatistics();
		}
	}

}
