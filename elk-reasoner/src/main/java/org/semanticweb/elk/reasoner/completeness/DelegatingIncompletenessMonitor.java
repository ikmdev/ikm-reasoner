
package org.semanticweb.elk.reasoner.completeness;



import org.slf4j.Logger;

/**
 * A {@link IncompletenessMonitor} that delegates all methods to the provided
 * {@link IncompletenessMonitor}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class DelegatingIncompletenessMonitor implements IncompletenessMonitor {

	private final IncompletenessMonitor delegate_;

	public DelegatingIncompletenessMonitor(IncompletenessMonitor delegate) {
		this.delegate_ = delegate;
	}

	public IncompletenessMonitor getDelegate() {
		return this.delegate_;
	}

	@Override
	public boolean isIncompletenessDetected() {
		return delegate_.isIncompletenessDetected();
	}

	@Override
	public void logStatus(Logger logger) {
		delegate_.logStatus(logger);
	}

	@Override
	public boolean isStatusChanged(Logger logger) {
		return delegate_.isStatusChanged(logger);
	}

}
