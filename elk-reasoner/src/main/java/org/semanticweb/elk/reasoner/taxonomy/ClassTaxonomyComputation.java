
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collection;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.reasoner.ProgressMonitor;
import org.semanticweb.elk.reasoner.ReasonerComputationWithInputs;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationStatistics;
import org.semanticweb.elk.reasoner.taxonomy.model.UpdateableTaxonomy;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

// TODO: documentation

/**
 * Computing taxonomy relations between atomic classes of the ontology.
 * 
 * @author Frantisek Simancik
 * @author Yevgeny Kazakov
 * 
 */
public class ClassTaxonomyComputation
		extends
		ReasonerComputationWithInputs<Collection<IndexedClass>, ClassTaxonomyComputationFactory> {

	public ClassTaxonomyComputation(
			Collection<? extends Collection<IndexedClass>> inputs,
			final InterruptMonitor interrupter,
			ConcurrentExecutor executor, int maxWorkers,
			ProgressMonitor progressMonitor, SaturationState<?> saturationState,
			UpdateableTaxonomy<ElkClass> partialTaxonomy) {
		super(inputs, new ClassTaxonomyComputationFactory(
				interrupter, saturationState, maxWorkers, partialTaxonomy),
				executor, maxWorkers, progressMonitor);
	}

	/**
	 * @return the taxonomy computed by this computation; the method
	 *         {@link #process()} should be called first to compute the taxonomy
	 */
	public UpdateableTaxonomy<ElkClass> getTaxonomy() {
		return processorFactory.getTaxonomy();
	}

	/**
	 * Print statistics about taxonomy computation
	 */
	public void printStatistics() {
		processorFactory.printStatistics();
	}

	public SaturationStatistics getRuleAndConclusionStatistics() {
		return processorFactory.getRuleAndConclusionStatistics();
	}

}