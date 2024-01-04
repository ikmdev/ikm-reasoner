
package org.semanticweb.elk.reasoner.completeness;

import org.slf4j.Logger;

/**
 * Monitors incompleteness triggered by a certain combination of
 * {@link Feature}s.
 * 
 * @author Peter Skocovsky
 * @author Yevgeny Kazakov
 */
class IncompletenessDueToUnsupportedFeatures extends DelegatingOccurrenceManager
		implements IncompletenessMonitor {

	/**
	 * The {@link Feature}s whose combination triggers potential incompleteness
	 */
	private final Feature[] unsupportedFeatures_;

	private String description_;

	boolean wasIncomplete_ = false;

	IncompletenessDueToUnsupportedFeatures(final OccurrenceManager occurrences,
			Feature... unsupportedOccurrences) {
		super(occurrences);
		this.unsupportedFeatures_ = unsupportedOccurrences;
	}

	@Override
	public boolean isIncompletenessDetected() {
		for (Feature occurrence : unsupportedFeatures_) {
			if (getOccurrenceCount(occurrence) <= 0) {
				return false;
			}
		}
		// else
		return true;
	}

	String getDescription() {
		if (description_ == null) {
			// lazy initialization
			StringBuilder descriptionBuilder = new StringBuilder();
			descriptionBuilder.append("Potential incompleteness due to ");
			for (int i = 0; i < unsupportedFeatures_.length; i++) {
				if (i > 0) {
					descriptionBuilder.append(" and ");
				}
				descriptionBuilder.append(unsupportedFeatures_[i]);
			}
			description_ = descriptionBuilder.toString();
		}
		return description_;
	}

	@Override
	public boolean isStatusChanged(Logger logger) {
		return logger.isInfoEnabled()
				&& isIncompletenessDetected() != wasIncomplete_;
	}
	
	@Override
	public void logStatus(Logger logger) {
		if (!logger.isInfoEnabled()) {
			return;
		}
		boolean isIncomplete = isIncompletenessDetected();
		if (wasIncomplete_ == isIncomplete) {
			// no change
			return;
		}
		wasIncomplete_ = isIncomplete;
		logger.info((isIncomplete ? "" : "[FIXED] ") + getDescription());
		if (isIncomplete) {
			for (Feature occurrence : unsupportedFeatures_) {
				super.logOccurrences(occurrence, logger);
			}
		}
	}	

}
