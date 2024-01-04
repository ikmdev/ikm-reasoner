
/**
 * @author Yevgeny Kazakov, Jul 1, 2011
 */
package org.semanticweb.elk.reasoner;

/**
 * A progress monitor that does nothing.
 * 
 * @author Yevgeny Kazakov
 * 
 */
public class DummyProgressMonitor implements ProgressMonitor {

	@Override
	public void start(String message) {
		// does nothing
	}

	@Override
	public void report(int state, int maxState) {
		// does nothing
	}

	@Override
	public void finish() {
		// does nothing
	}

}
