
package org.semanticweb.elk.util.concurrent.computation;

/**
 * A {@link InterruptMonitor} that delegates the calls to the delegate specified
 * in the constructor.
 * 
 * @author Peter Skocovsky
 */
public class DelegateInterruptMonitor implements InterruptMonitor {

	/**
	 * The {@link InterruptMonitor} that is checked for interruptions.
	 */
	private final InterruptMonitor interrupter_;

	/**
	 * @param interrupter
	 *            The {@link InterruptMonitor} to which the calls are delegated.
	 *            Must <strong>not</strong> be {@code null}!
	 */
	public DelegateInterruptMonitor(final InterruptMonitor interrupter) {
		if (interrupter == null) {
			throw new IllegalArgumentException("delegate must not be null!");
		}
		this.interrupter_ = interrupter;
	}

	@Override
	public boolean isInterrupted() {
		return interrupter_.isInterrupted();
	}

}
