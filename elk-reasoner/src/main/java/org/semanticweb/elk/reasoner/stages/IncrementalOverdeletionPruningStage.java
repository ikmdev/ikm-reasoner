
package org.semanticweb.elk.reasoner.stages;

import java.util.Collection;

import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.ClassExpressionSaturation;
import org.semanticweb.elk.reasoner.saturation.context.ContextRootCollection;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationAdditionPruningFactory;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationFactory;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationInput;

/**
 * Prunes the set of deleted conclusions by re-deriving those having alternative
 * derivations (name taken from the original paper on the DRed algorithm). Uses
 * a {@link RuleApplicationAdditionPruningFactory} which "fills gaps" in the set of
 * conclusions for each context by deriving the missing conclusions.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class IncrementalOverdeletionPruningStage extends AbstractReasonerStage {

	private ClassExpressionSaturation<IndexedContextRoot> completion_ = null;

	public IncrementalOverdeletionPruningStage(AbstractReasonerState reasoner,
			AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	public String getName() {
		return "Incremental Overdeletion Pruning";
	}

	@Override
	public void printInfo() {
		if (completion_ != null) {
			completion_.printStatistics();
		}
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute()) {
			return false;
		}

		RuleApplicationFactory<?, RuleApplicationInput> ruleAppFactory = new RuleApplicationAdditionPruningFactory(
				reasoner.getInterrupter(), reasoner.saturationState);
		Collection<IndexedContextRoot> inputs = new ContextRootCollection(
				reasoner.saturationState.getNotSaturatedContexts());

		completion_ = new ClassExpressionSaturation<IndexedContextRoot>(inputs,
				reasoner.getProcessExecutor(), workerNo,
				reasoner.getProgressMonitor(), ruleAppFactory);

		return true;
	}

	@Override
	void executeStage() throws ElkException {
		completion_.process();
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute()) {
			return false;
		}
		reasoner.ruleAndConclusionStats.add(completion_
				.getRuleAndConclusionStatistics());
		this.completion_ = null;
		return true;
	}

}
