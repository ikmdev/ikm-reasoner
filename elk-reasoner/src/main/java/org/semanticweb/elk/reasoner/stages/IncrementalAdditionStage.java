
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.reasoner.incremental.IncrementalStages;
import org.semanticweb.elk.reasoner.saturation.ClassExpressionSaturationNoInput;
import org.semanticweb.elk.reasoner.saturation.ContextModificationListener;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationAdditionUnSaturationFactory;

/**
 * TODO docs
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class IncrementalAdditionStage extends AbstractReasonerStage {

	private ClassExpressionSaturationNoInput saturation_ = null;

	public IncrementalAdditionStage(AbstractReasonerState reasoner,
			AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	public String getName() {
		return IncrementalStages.ADDITION.toString();
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute()) {
			return false;
		}

		saturation_ = new ClassExpressionSaturationNoInput(
				reasoner.getProcessExecutor(), workerNo,
				new RuleApplicationAdditionUnSaturationFactory(
						reasoner.getInterrupter(), reasoner.saturationState),
				ContextModificationListener.DUMMY);

		return true;
	}

	@Override
	public void executeStage() throws ElkInterruptedException {
		saturation_.process();
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute())
			return false;
		reasoner.ruleAndConclusionStats.add(saturation_
				.getRuleAndConclusionStatistics());
		// at this point we're done with unsaturated contexts
		markAllContextsAsSaturated();
		this.saturation_ = null;
		return true;
	}

	@Override
	public void printInfo() {
		if (saturation_ != null)
			saturation_.printStatistics();
	}

}