
package org.semanticweb.elk.reasoner.completeness;



import org.slf4j.Logger;

/**
 * An {@link OccurrenceCounter} that additionally keeps track of some recent
 * occurrences of {@link Feature}s.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface OccurrenceManager extends OccurrenceCounter {

	/**
	 * Prints some message about occurrences of the given {@link Feature}, which
	 * may include the the number of its occurrences or details about some
	 * recent occurrences
	 * 
	 * @param feature
	 * @param logger
	 */
	void logOccurrences(Feature feature, Logger logger);

	/**
	 * @param occurrence
	 * @return {@code true} if some new occurrences of the given {@link Feature}
	 *         may have appeared after the last call of
	 *         {@link #logOccurrences(Feature, Logger)}, and {@code false}
	 *         otherwise
	 */
	boolean hasNewOccurrencesOf(Feature occurrence);

}
