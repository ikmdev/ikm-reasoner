
package org.semanticweb.elk.reasoner.completeness;



import org.slf4j.Logger;

/**
 * An {@link OccurrenceManager} that aggregates information about several other
 * {@link OccurrenceManager}s, as if all occurrences of the {@link Feature}s are
 * taken together.
 * 
 * @author Yevgeny Kazakov
 */
public class CombinedOccurrenceManager implements OccurrenceManager {

	OccurrenceManager[] managers_;

	public CombinedOccurrenceManager(OccurrenceManager... managers) {
		this.managers_ = managers;
	}

	@Override
	public int getOccurrenceCount(Feature occurrence) {
		int sum = 0;
		for (OccurrenceManager manager : managers_) {
			sum += manager.getOccurrenceCount(occurrence);
		}
		return sum;
	}

	@Override
	public void logOccurrences(Feature occurrence, Logger logger) {
		for (OccurrenceManager manager : managers_) {
			manager.logOccurrences(occurrence, logger);
		}
	}

	@Override
	public boolean hasNewOccurrencesOf(Feature occurrence) {
		for (OccurrenceManager manager : managers_) {
			if (manager.hasNewOccurrencesOf(occurrence)) {
				return true;
			}
		}
		// else
		return false;
	}

}
