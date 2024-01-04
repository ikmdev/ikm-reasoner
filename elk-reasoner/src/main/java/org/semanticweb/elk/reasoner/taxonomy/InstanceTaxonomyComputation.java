
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collection;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.reasoner.ProgressMonitor;
import org.semanticweb.elk.reasoner.ReasonerComputationWithInputs;
import org.semanticweb.elk.reasoner.indexing.model.IndexedIndividual;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.taxonomy.model.UpdateableInstanceTaxonomy;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

// TODO: documentation

/**
 * Computing taxonomy relations between named individuals and atomic classes of
 * the ontology.
 * 
 * @author Frantisek Simancik
 * @author Yevgeny Kazakov
 * 
 */
public class InstanceTaxonomyComputation
		extends
		ReasonerComputationWithInputs<IndexedIndividual, InstanceTaxonomyComputationFactory> {

	public InstanceTaxonomyComputation(
			Collection<? extends IndexedIndividual> inputs,
			final InterruptMonitor interrupter,
			ConcurrentExecutor executor,
			int maxWorkers,
			ProgressMonitor progressMonitor,
			SaturationState<?> saturationState,
			UpdateableInstanceTaxonomy<ElkClass, ElkNamedIndividual> partialTaxonomy) {
		super(inputs, new InstanceTaxonomyComputationFactory(
				interrupter, saturationState, maxWorkers, partialTaxonomy),
				executor, maxWorkers, progressMonitor);
	}

	/**
	 * @return the taxonomy computed by this computation; the method
	 *         {@link #process()} should be called first to compute the taxonomy
	 */
	public UpdateableInstanceTaxonomy<ElkClass, ElkNamedIndividual> getTaxonomy() {
		return processorFactory.getTaxonomy();
	}

	/**
	 * Print statistics about taxonomy computation
	 */
	public void printStatistics() {
		processorFactory.printStatistics();
	}

}