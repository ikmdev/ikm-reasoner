
package org.semanticweb.elk.reasoner.completeness;



import org.slf4j.Logger;

/**
 * An {@link OccurrenceManager} that delegates all method calls to a given
 * {@link OccurrenceManager}
 * 
 * @author Yevgeny Kazakov
 */
public class DelegatingOccurrenceManager extends DelegatingOccurrenceCounter
		implements OccurrenceManager {

	private OccurrenceManager delegate_;

	public DelegatingOccurrenceManager(OccurrenceManager delegate) {
		super(delegate);
		this.delegate_ = delegate;
	}

	@Override
	public void logOccurrences(Feature occurrence, Logger logger) {
		delegate_.logOccurrences(occurrence, logger);
	}

	@Override
	public boolean hasNewOccurrencesOf(Feature occurrence) {
		return delegate_.hasNewOccurrencesOf(occurrence);
	}

}
