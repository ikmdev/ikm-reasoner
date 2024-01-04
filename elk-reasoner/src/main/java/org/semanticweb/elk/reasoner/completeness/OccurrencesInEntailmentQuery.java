
package org.semanticweb.elk.reasoner.completeness;

import java.util.EnumSet;
import java.util.Set;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.slf4j.Logger;

/**
 * An {@link OccurrenceManager} that provides information about occurrences of
 * {@link Feature}s in an entailment query for an {@link ElkAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public class OccurrencesInEntailmentQuery implements OccurrenceManager {

	private final ElkAxiom query_;

	private final OccurrenceCounter occurrenceCounter_;

	private Set<Feature> reportedOccurrences_ = EnumSet.noneOf(Feature.class);

	public OccurrencesInEntailmentQuery(ElkAxiom query,
			OccurrenceCounter occurrenceCounter) {
		this.query_ = query;
		this.occurrenceCounter_ = occurrenceCounter;
	}

	/**
	 * @return the {@link ElkAxiom} query for which the information about
	 *         occurrences of {@link Feature}s is maintained
	 */
	ElkAxiom getQuery() {
		return query_;
	}

	@Override
	public int getOccurrenceCount(Feature occurrence) {
		return occurrenceCounter_.getOccurrenceCount(occurrence);
	}

	@Override
	public void logOccurrences(Feature occurrence, Logger logger) {
		int count = getOccurrenceCount(occurrence);
		String occurrencesString = count == 1 ? "occurrence" : "occurrences";
		String polarityString = "";
		// unsupported polarities in query should be inverted
		switch (occurrence.getPolarity()) {
		case POSITIVE:
			polarityString = "negative ";
			break;
		case NEGATIVE:
			polarityString = "positive ";
		default:
			break;
		}
		logger.info("{} {}{} of {} found in the entailment query {}", count,
				polarityString, occurrencesString, occurrence.getConstructor(),
				query_);
	}

	@Override
	public boolean hasNewOccurrencesOf(Feature occurrence) {
		return !reportedOccurrences_.contains(occurrence)
				&& getOccurrenceCount(occurrence) > 0;
	}

}
