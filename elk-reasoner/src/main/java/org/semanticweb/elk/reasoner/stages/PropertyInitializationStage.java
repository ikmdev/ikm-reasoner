
package org.semanticweb.elk.reasoner.stages;

import java.util.Iterator;

import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.properties.SaturatedPropertyChain;

//TODO: add progress monitor, make concurrent if possible

/**
 * A {@link ReasonerStage} which deletes all derived information from
 * {@link SaturatedPropertyChain} assigned to {@link IndexedPropertyChain}s
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 */
class PropertyInitializationStage extends AbstractReasonerStage {

	/**
	 * The dispatcher of events over derived property hierarchy and
	 * compositions.
	 */
	private final PropertyHierarchyCompositionState.Dispatcher dispatcher_;

	/**
	 * The progress counter
	 */
	private int progress_;
	/**
	 * The number of contexts
	 */
	private int maxProgress_;

	/**
	 * The state of the iterator of the input to be processed
	 */
	private Iterator<? extends IndexedPropertyChain> todo_ = null;

	public PropertyInitializationStage(AbstractReasonerState reasoner,
			AbstractReasonerStage... preStages) {
		super(reasoner, preStages);
		this.dispatcher_ = reasoner.propertyHierarchyCompositionState_
				.getDispatcher();
	}

	@Override
	public String getName() {
		return "Property Saturation Initialization";
	}

	@Override
	public boolean preExecute() {
		if (!super.preExecute())
			return false;
		todo_ = reasoner.ontologyIndex.getPropertyChains().iterator();
		maxProgress_ = reasoner.ontologyIndex.getPropertyChains().size();
		progress_ = 0;
		return true;
	}

	@Override
	public void executeStage() throws ElkInterruptedException {
		for (;;) {
			/* 
			 * TODO: Cannot be interrupted, because it remembers iterator over
			 * properties and if some property disappears before restarted,
			 * ConcurrentModificationException is thrown. For other stages this
			 * is solved by invalidating when input changes, but here we do not
			 * want to invalidate when a property only disappears.
			 */
			// checkInterrupt();
			if (!todo_.hasNext())
				break;
			IndexedPropertyChain ipc = todo_.next();
			SaturatedPropertyChain saturation = ipc.getSaturated();
			saturation.clear();
			dispatcher_.firePropertyBecameNotSaturated(ipc);
			reasoner.getProgressMonitor().report(++progress_, maxProgress_);
		}
	}

	@Override
	public boolean postExecute() {
		if (!super.postExecute())
			return false;
		this.todo_ = null;
		return true;
	}

	@Override
	public void printInfo() {
		// nothing interesting to print
	}

}
