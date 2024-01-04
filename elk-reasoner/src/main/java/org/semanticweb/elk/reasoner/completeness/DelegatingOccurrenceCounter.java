
package org.semanticweb.elk.reasoner.completeness;



/**
 * An {@link OccurrenceCounter} that delegates all method calls to a given
 * {@link OccurrenceCounter}
 * 
 * @author Yevgeny Kazakov
 */
public class DelegatingOccurrenceCounter implements OccurrenceCounter {

	private final OccurrenceCounter delegate_;

	public DelegatingOccurrenceCounter(OccurrenceCounter delegate) {
		this.delegate_ = delegate;
	}

	@Override
	public int getOccurrenceCount(Feature occurrence) {
		return delegate_.getOccurrenceCount(occurrence);
	}

}
