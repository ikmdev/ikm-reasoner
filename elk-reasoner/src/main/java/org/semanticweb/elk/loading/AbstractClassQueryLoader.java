
package org.semanticweb.elk.loading;

import org.semanticweb.elk.util.concurrent.computation.DelegateInterruptMonitor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A skeletal implementation of the {@link ClassQueryLoader} that minimizes the
 * effort to implement the interface.
 * 
 * @author Peter Skocovsky
 */
public abstract class AbstractClassQueryLoader extends DelegateInterruptMonitor
		implements ClassQueryLoader {

	public AbstractClassQueryLoader(final InterruptMonitor interrupter) {
		super(interrupter);
	}

	@Override
	public void dispose() {
		// does nothing
	}

}
