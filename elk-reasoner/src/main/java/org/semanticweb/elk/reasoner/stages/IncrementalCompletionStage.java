
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.reasoner.incremental.IncrementalStages;
import org.semanticweb.elk.reasoner.saturation.ClassExpressionSaturationNoInput;
import org.semanticweb.elk.reasoner.saturation.ContextModificationListener;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationAdditionFactory;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationInput;

/**
 * Completes saturation of all contexts which are not saturated at this point.
 * Useful, for example, to continue saturation after the ontology was proved
 * inconsistent and all workers have stopped, possibly in the middle of
 * saturation.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class IncrementalCompletionStage extends AbstractReasonerStage {

	// logger for this class
	// private static final Logger LOGGER_ =
	// LoggerFactory.getLogger(IncrementalCompletionStage.class);

	private ClassExpressionSaturationNoInput completion_ = null;

	public IncrementalCompletionStage(AbstractReasonerState reasoner,
			AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	public String getName() {
		return IncrementalStages.COMPLETION.toString();
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute())
			return false;
		completion_ = new ClassExpressionSaturationNoInput(
				reasoner.getProcessExecutor(), workerNo,
				new RuleApplicationAdditionFactory<RuleApplicationInput>(
						reasoner.getInterrupter(), reasoner.saturationState),
				ContextModificationListener.DUMMY);
		return true;
	}

	@Override
	public void executeStage() throws ElkException {
		completion_.process();
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute())
			return false;
		reasoner.ruleAndConclusionStats.add(completion_
				.getRuleAndConclusionStatistics());
		this.completion_ = null;
		return true;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
	}

}
