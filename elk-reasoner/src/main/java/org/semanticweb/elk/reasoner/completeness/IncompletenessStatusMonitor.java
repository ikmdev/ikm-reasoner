
package org.semanticweb.elk.reasoner.completeness;



import org.slf4j.Logger;

/**
 * An {@link IncompletenessMonitor} that keeps track of changes in
 * incompleteness status for a given {@link IncompletenessMonitor}. It only
 * reports changes to the completeness of the reasoning task as warnings, i.e.,
 * if the reasoning task was complete but becomes incomplete, or if the
 * reasoning task was incomplete and then becomes complete.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class IncompletenessStatusMonitor
		extends DelegatingIncompletenessMonitor {

	private final String statusMessage_;
	private boolean isIncompletenessDetected_ = false;

	public IncompletenessStatusMonitor(IncompletenessMonitor delegate,
			String statusMessage) {
		super(delegate);
		this.statusMessage_ = statusMessage;
	}

	@Override
	public boolean isStatusChanged(Logger logger) {
		return logger.isWarnEnabled()
				&& (isIncompletenessDetected_ != isIncompletenessDetected()
						|| super.isStatusChanged(logger));
	}

	@Override
	public void logStatus(Logger logger) {
		if (!logger.isWarnEnabled()) {
			return;
		}
		boolean isIncompletenessDetected = isIncompletenessDetected();
		if (isIncompletenessDetected_ == isIncompletenessDetected
				&& !super.isStatusChanged(logger)) {
			return;
		}
		isIncompletenessDetected_ = isIncompletenessDetected;
		if (isIncompletenessDetected_) {
			logger.warn(statusMessage_);
		} else {
			logger.warn("[Fixed] " + statusMessage_);
		}
		super.logStatus(logger);
	}

}
