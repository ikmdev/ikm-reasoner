
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassEntity;
import org.semanticweb.elk.reasoner.saturation.ClassExpressionSaturation;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationAdditionFactory;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationInput;

/**
 * A {@link ReasonerStage} during which consistency of the current ontology is
 * checked
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
class ConsistencyCheckingStage extends AbstractReasonerStage {

	/**
	 * the computation used for this stage
	 */
	protected ClassExpressionSaturation<IndexedClassEntity> computation = null;

	public ConsistencyCheckingStage(AbstractReasonerState reasoner,
			AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	public String getName() {
		return "Consistency Checking";
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute())
			return false;
		this.computation = new ClassExpressionSaturation<IndexedClassEntity>(
				reasoner.consistencyCheckingState.getTestEntitites(),
				reasoner.getProcessExecutor(), workerNo,
				reasoner.getProgressMonitor(),
				new RuleApplicationAdditionFactory<RuleApplicationInput>(
						reasoner.getInterrupter(), reasoner.saturationState));
		return true;
	}

	@Override
	public void executeStage() throws ElkInterruptedException {
		computation.process();
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute())
			return false;
		reasoner.ruleAndConclusionStats
				.add(computation.getRuleAndConclusionStatistics());
		this.computation = null;
		// FIXME Obviously needed a better clean-up after inconsistency
		if (reasoner.consistencyCheckingState.isInconsistent()) {
			reasoner.classTaxonomyState.resetTaxonomy();
			reasoner.stageManager.objectPropertyTaxonomyComputationStage
					.invalidate();
		}
		return true;
	}

	@Override
	public void printInfo() {
		if (computation != null)
			computation.printStatistics();
	}

}
