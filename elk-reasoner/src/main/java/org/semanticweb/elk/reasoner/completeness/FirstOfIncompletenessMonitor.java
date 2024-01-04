
package org.semanticweb.elk.reasoner.completeness;

import java.util.Collection;



import org.slf4j.Logger;

/**
 * An {@link IncompletenessMonitor} that monitors several other
 * {@link IncompletenessMonitor}s and reports incompleteness if some of these
 * monitors report incompleteness. The status messages include information of
 * monitors until the first monitor that reports incompleteness.
 * 
 * @author Yevgeny Kazakov
 */
public class FirstOfIncompletenessMonitor implements IncompletenessMonitor {

	/**
	 * The monitors that are combined
	 */
	private final IncompletenessMonitor[] monitors_;

	FirstOfIncompletenessMonitor(IncompletenessMonitor... monitors) {
		this.monitors_ = monitors;
	}

	FirstOfIncompletenessMonitor(Collection<IncompletenessMonitor> monitors) {
		this(monitors.toArray(new IncompletenessMonitor[0]));
	}

	@Override
	public boolean isIncompletenessDetected() {
		for (IncompletenessMonitor monitor : monitors_) {
			if (monitor.isIncompletenessDetected()) {
				return true;
			}
		}
		// else
		return false;
	}

	@Override
	public boolean isStatusChanged(Logger logger) {
		for (IncompletenessMonitor monitor : monitors_) {
			if (monitor.isStatusChanged(logger)) {
				return true;
			}
			if (monitor.isIncompletenessDetected()) {
				return false;
			}
		}
		return false;
	}

	@Override
	public void logStatus(Logger logger) {
		for (IncompletenessMonitor monitor : monitors_) {
			monitor.logStatus(logger);
			if (monitor.isIncompletenessDetected()) {
				return;
			}
		}
	}

}
