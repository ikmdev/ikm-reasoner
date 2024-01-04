
package org.semanticweb.elk.reasoner.saturation;

import org.semanticweb.elk.reasoner.ReasonerComputation;
import org.semanticweb.elk.reasoner.saturation.rules.factories.RuleApplicationFactory;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;

/**
 * A {@link ReasonerComputation} that uses a given
 * {@link RuleApplicationFactory} to process the {@link SaturationState} without
 * any further input.
 * 
 * @author Pavel Klinov
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ClassExpressionSaturationNoInput extends
		ReasonerComputation<ClassExpressionSaturationNoInputFactory> {

	public ClassExpressionSaturationNoInput(final ConcurrentExecutor executor,
			final int maxWorkers,
			final RuleApplicationFactory<?, ?> ruleAppFactory,
			final ContextModificationListener contextModificationListener) {

		super(new ClassExpressionSaturationNoInputFactory(ruleAppFactory,
				contextModificationListener), executor, maxWorkers);
	}

	/**
	 * Print statistics about the saturation computation
	 */

	public void printStatistics() {
		processorFactory.printStatistics();
	}

	public SaturationStatistics getRuleAndConclusionStatistics() {
		return processorFactory.getRuleAndConclusionStatistics();
	}

}
