
package org.semanticweb.elk.reasoner.completeness;

import java.util.EnumSet;
import java.util.Set;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.slf4j.Logger;

/**
 * An {@link OccurrenceManager} that provides information about occurrences of
 * {@link Feature}s in a given {@link ElkClassExpression} query.
 * 
 * @author Yevgeny Kazakov
 */
public class OccurrencesInClassExpressionQuery implements OccurrenceManager {

	private final ElkClassExpression query_;

	private final OccurrenceCounter occurrenceCounter_;

	private Set<Feature> reportedOccurrences_ = EnumSet.noneOf(Feature.class);

	public OccurrencesInClassExpressionQuery(ElkClassExpression query,
			OccurrenceCounter occurrenceCounter) {
		this.query_ = query;
		this.occurrenceCounter_ = occurrenceCounter;
	}

	/**
	 * @return the {@link ElkClassExpression} query for which the information
	 *         about occurrences of {@link Feature}s is maintained
	 */
	ElkClassExpression getQuery() {
		return query_;
	}

	@Override
	public int getOccurrenceCount(Feature occurrence) {
		return occurrenceCounter_.getOccurrenceCount(occurrence);
	}

	@Override
	public void logOccurrences(Feature occurrence, Logger logger) {
		int count = getOccurrenceCount(occurrence);
		String occurrences = count == 1 ? "occurrence" : "occurrences";
		logger.info("{} {} of {} found in the queried class expression {}",
				count, occurrences, occurrence.getConstructor(), query_);
		reportedOccurrences_.add(occurrence);
	}

	@Override
	public boolean hasNewOccurrencesOf(Feature occurrence) {
		return !reportedOccurrences_.contains(occurrence)
				&& getOccurrenceCount(occurrence) > 0;
	}

}
