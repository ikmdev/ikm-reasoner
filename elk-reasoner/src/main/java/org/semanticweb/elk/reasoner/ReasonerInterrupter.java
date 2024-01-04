
package org.semanticweb.elk.reasoner;

import org.semanticweb.elk.reasoner.stages.ElkInterruptedException;
import org.semanticweb.elk.util.concurrent.computation.Interrupter;

/**
 * A simple interrupter, which uses a flag about the interrupt status.
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 */
public class ReasonerInterrupter implements Interrupter {

	/**
	 * The interruption status of this interrupter.
	 */
	private volatile boolean isInterrupted_ = false;

	@Override
	public void interrupt() {
		isInterrupted_ = true;
	}

	@Override
	public boolean isInterrupted() {
		return isInterrupted_;
	}

	/**
	 * If interrupted, clears the flag and throws ElkInterruptedException
	 * 
	 * @throws ElkInterruptedException
	 *             if interrupted
	 */
	public void checkInterrupt() throws ElkInterruptedException {
		if (isInterrupted()) {
			isInterrupted_ = false;
			throw new ElkInterruptedException();
		}
	}

}
