
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.reasoner.incremental.IncrementalChangesInitialization;
import org.semanticweb.elk.reasoner.incremental.IncrementalStages;
import org.semanticweb.elk.reasoner.saturation.SaturationStatistics;

/**
 * The base stage for initializing additions and deletions
 * 
 * @author Pavel Klinov
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractIncrementalChangesInitializationStage extends
		AbstractReasonerStage {

	protected IncrementalChangesInitialization initialization = null;

	protected SaturationStatistics stageStatistics = null;

	public AbstractIncrementalChangesInitializationStage(
			AbstractReasonerState reasoner, AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	protected abstract IncrementalStages stage();

	@Override
	public String getName() {
		return stage().toString();
	}

	@Override
	public void executeStage() throws ElkInterruptedException {
		if (isInterrupted())
			return;
		initialization.process();
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute())
			return false;
		this.stageStatistics = new SaturationStatistics();
		return true;
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute())
			return false;

		reasoner.ruleAndConclusionStats.add(stageStatistics);
		this.stageStatistics = null;
		this.initialization = null;

		return true;
	}

	@Override
	public void printInfo() {
		// TODO
	}

}