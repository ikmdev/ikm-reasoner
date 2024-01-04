
package org.semanticweb.elk.reasoner.saturation.properties;

import java.util.ArrayList;
import java.util.Collection;

import org.liveontologies.puli.Producer;
import org.semanticweb.elk.reasoner.ProgressMonitor;
import org.semanticweb.elk.reasoner.ReasonerComputationWithInputs;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.OntologyIndex;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.ObjectPropertyInference;
import org.semanticweb.elk.reasoner.stages.PropertyHierarchyCompositionState;
import org.semanticweb.elk.util.concurrent.computation.ConcurrentExecutor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A {@link ReasonerComputationWithInputs} that computes relevant sub-properties
 * and composition maps
 * 
 * @author Yevgeny Kazakov
 * 
 */
public class PropertyHierarchyCompositionComputation extends
		ReasonerComputationWithInputs<IndexedPropertyChain, PropertyHierarchyCompositionComputationFactory> {

	public PropertyHierarchyCompositionComputation(OntologyIndex ontIndex,
			final InterruptMonitor interrupter,
			Producer<? super ObjectPropertyInference> inferenceProducer,
			final PropertyHierarchyCompositionState.Dispatcher dispatcher,
			ConcurrentExecutor executor, int maxWorkers,
			ProgressMonitor progressMonitor) {
		// TODO: temporary solution against concurrent modification exception
		this(new ArrayList<IndexedPropertyChain>(ontIndex.getPropertyChains()),
				new PropertyHierarchyCompositionComputationFactory(interrupter,
						inferenceProducer, dispatcher),
				executor, maxWorkers, progressMonitor);
	}

	PropertyHierarchyCompositionComputation(
			Collection<? extends IndexedPropertyChain> inputs,
			PropertyHierarchyCompositionComputationFactory inputProcessorFactory,
			ConcurrentExecutor executor, int maxWorkers,
			ProgressMonitor progressMonitor) {
		super(inputs, inputProcessorFactory, executor, maxWorkers,
				progressMonitor);
	}

}
