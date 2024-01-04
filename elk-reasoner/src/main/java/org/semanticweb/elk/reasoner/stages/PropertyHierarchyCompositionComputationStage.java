
package org.semanticweb.elk.reasoner.stages;

import org.liveontologies.puli.Producer;
import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.reasoner.saturation.properties.PropertyHierarchyCompositionComputation;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.ObjectPropertyInference;

public class PropertyHierarchyCompositionComputationStage
		extends AbstractReasonerStage {

	/**
	 * the computation used for this stage
	 */
	private PropertyHierarchyCompositionComputation computation_ = null;

	public PropertyHierarchyCompositionComputationStage(
			AbstractReasonerState reasoner,
			AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
	}

	@Override
	public String getName() {
		return "Object Property Hierarchy and Composition Computation";
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute()) {
			return false;
		}

		Producer<? super ObjectPropertyInference> inferenceProducer = reasoner
				.getTraceState();

		computation_ = new PropertyHierarchyCompositionComputation(
				reasoner.ontologyIndex, reasoner.getInterrupter(),
				inferenceProducer,
				reasoner.propertyHierarchyCompositionState_.getDispatcher(),
				reasoner.getProcessExecutor(), workerNo,
				reasoner.getProgressMonitor());

		return true;
	}

	@Override
	public void executeStage() throws ElkException {
		computation_.process();
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute())
			return false;
		this.computation_ = null;
		return true;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub

	}

}
