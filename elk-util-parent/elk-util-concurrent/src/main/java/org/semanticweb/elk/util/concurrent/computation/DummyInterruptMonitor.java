
package org.semanticweb.elk.util.concurrent.computation;

/**
 * Can never be interrupted. Always returns {@code false} from
 * {@link #isInterrupted()}.
 * 
 * @author Peter Skocovsky
 */
public class DummyInterruptMonitor implements InterruptMonitor {

	public static final DummyInterruptMonitor INSTANCE = new DummyInterruptMonitor();

	@Override
	public boolean isInterrupted() {
		return false;
	}

}
