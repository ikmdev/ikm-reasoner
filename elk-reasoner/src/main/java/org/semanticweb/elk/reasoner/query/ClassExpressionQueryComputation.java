
package org.semanticweb.elk.reasoner.query;

import java.util.Collection;

import org.semanticweb.elk.reasoner.ProgressMonitor;
import org.semanticweb.elk.reasoner.ReasonerComputationWithInputs;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.reduction.TransitiveReductionOutputVisitor;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationStatistics;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * Computes the direct super-, direct sub-, and equivalent classes of the class
 * expressions supplied as input.
 * 
 * @author Peter Skocovsky
 */
public class ClassExpressionQueryComputation extends
		ReasonerComputationWithInputs<IndexedClassExpression, ClassExpressionQueryComputationFactory> {

	public ClassExpressionQueryComputation(
			final Collection<IndexedClassExpression> inputs,
			final InterruptMonitor interrupter,
			final ConcurrentExecutor executor, final int maxWorkers,
			final ProgressMonitor progressMonitor,
			final SaturationState<?> saturationState,
			final TransitiveReductionOutputVisitor<IndexedClassExpression> outputProcessor) {
		super(inputs,
				new ClassExpressionQueryComputationFactory(interrupter,
						saturationState, maxWorkers, outputProcessor),
				executor, maxWorkers, progressMonitor);
	}

	public void printStatistics() {
		processorFactory.printStatistics();
	}

	public SaturationStatistics getRuleAndConclusionStatistics() {
		return processorFactory.getRuleAndConclusionStatistics();
	}

}