
package org.semanticweb.elk.reasoner.completeness;



/**
 * An {@link OccurrenceCounter} that always returns 0 occurrences for every
 * {@link Feature}
 * 
 * @author Yevgeny Kazakov
 */
public class EmptyOccurrenceCounter implements OccurrenceCounter {

	private static OccurrenceCounter INSTANCE_ = new EmptyOccurrenceCounter();

	@Override
	public int getOccurrenceCount(Feature occurrence) {
		return 0;
	}

	public static OccurrenceCounter get() {
		return INSTANCE_;
	}

}
