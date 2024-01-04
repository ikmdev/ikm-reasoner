
package org.semanticweb.elk.loading;

import org.semanticweb.elk.owl.visitors.ElkAxiomProcessor;
import org.semanticweb.elk.util.concurrent.computation.DelegateInterruptMonitor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A skeletal implementation of the {@link AxiomLoader} that minimizes the
 * effort to implement the interface.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public abstract class AbstractAxiomLoader extends DelegateInterruptMonitor implements
		AxiomLoader {

	public AbstractAxiomLoader(final InterruptMonitor interrupter) {
		super(interrupter);
	}

	@Override
	public abstract void load(ElkAxiomProcessor axiomInserter,
			ElkAxiomProcessor axiomDeleter) throws ElkLoadingException;

	@Override
	public abstract boolean isLoadingFinished();

	@Override
	public void dispose() {
		// does nothing
	}

}
