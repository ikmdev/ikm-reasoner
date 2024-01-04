
package org.semanticweb.elk.loading;

import org.semanticweb.elk.util.concurrent.computation.DelegateInterruptMonitor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A skeletal implementation of the {@link EntailmentQueryLoader} that minimizes
 * the effort to implement the interface.
 * 
 * @author Peter Skocovsky
 */
public abstract class AbstractEntailmentQueryLoader
		extends DelegateInterruptMonitor implements EntailmentQueryLoader {

	public AbstractEntailmentQueryLoader(final InterruptMonitor interrupter) {
		super(interrupter);
	}

	@Override
	public void dispose() {
		// Empty.
	}

}
