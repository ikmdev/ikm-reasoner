
package org.semanticweb.elk.reasoner.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.incremental.IncrementalChangesInitialization;
import org.semanticweb.elk.reasoner.incremental.IncrementalStages;
import org.semanticweb.elk.reasoner.indexing.classes.DifferentialIndex;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ContextInitializationNoPremises;
import org.semanticweb.elk.reasoner.saturation.rules.contextinit.LinkedContextInitRule;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.LinkedSubsumerRule;
import org.semanticweb.elk.util.collections.Operations;

/**
 * 
 * 
 */
public class IncrementalDeletionInitializationStage
		extends
			AbstractIncrementalChangesInitializationStage {

	public IncrementalDeletionInitializationStage(
			AbstractReasonerState reasoner,
			AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	protected IncrementalStages stage() {
		return IncrementalStages.DELETIONS_INIT;
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute())
			return false;

		DifferentialIndex diffIndex = reasoner.ontologyIndex;
		LinkedContextInitRule changedInitRules = null;
		Map<? extends IndexedClassExpression, ? extends LinkedSubsumerRule> changedRulesByCE = null;
		Map<? extends IndexedClass, ? extends IndexedClassExpression> changedDefinitions = null;
		Map<? extends IndexedClass, ? extends ElkAxiom> changedDefinitionReasons = null;
		Collection<ArrayList<Context>> inputs = Collections.emptyList();

		changedInitRules = diffIndex.getRemovedContextInitRules();
		changedRulesByCE = diffIndex.getRemovedContextRulesByClassExpressions();
		changedDefinitions = diffIndex.getRemovedDefinitions();
		changedDefinitionReasons = diffIndex.getRemovedDefinitionReasons();

		if (changedInitRules != null || !changedRulesByCE.isEmpty()
				|| !changedDefinitions.isEmpty()) {

			inputs = Operations.split(reasoner.saturationState.getContexts(),
					8 * workerNo);
		}

		// System.err.println(changedRulesByCE.keySet().size());

		this.initialization = new IncrementalChangesInitialization(inputs,
				reasoner.getInterrupter(),
				changedInitRules, changedRulesByCE, changedDefinitions,
				changedDefinitionReasons, reasoner.saturationState,
				reasoner.getProcessExecutor(), stageStatistics, workerNo,
				reasoner.getProgressMonitor());

		return true;
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute())
			return false;
		this.initialization = null;
		// initializing contexts which will be removed
		final SaturationStateWriter<?> satStateWriter = reasoner.saturationState
				.getContextCreatingWriter();

		for (IndexedClassExpression ice : reasoner.ontologyIndex
				.getRemovedClassExpressions()) {

			if (reasoner.saturationState.getContext(ice) != null) {
				satStateWriter
						.produce(new ContextInitializationNoPremises(ice));
			}
		}

		reasoner.ontologyIndex.clearDeletedRules();

		return true;
	}
}
