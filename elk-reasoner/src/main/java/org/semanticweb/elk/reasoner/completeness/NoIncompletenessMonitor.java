 
package org.semanticweb.elk.reasoner.completeness;



import org.slf4j.Logger;

/**
 * An {@link IncompletenessMonitor} that never reports incompleteness. I.e., it
 * assumes that the result is complete.
 * 
 * @author Yevgeny Kazakov
 *
 */
class NoIncompletenessMonitor implements IncompletenessMonitor {

	static NoIncompletenessMonitor INSTNANCE = new NoIncompletenessMonitor();

	@Override
	public boolean isIncompletenessDetected() {
		return false;
	}

	@Override
	public boolean isStatusChanged(Logger logger) {
		return false;
	}

	@Override
	public void logStatus(Logger logger) {
		// nothing to explain
	}

}
