
package org.semanticweb.elk.util.logging.statistics;



/**
 * A skeleton class for implementing counters and timers for statistical
 * information.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public abstract class AbstractStatistics {

	/**
	 * The number of times measurements were taken in different threads. Used to
	 * average the time results.
	 */
	private int numOfMeasurements_ = 0;

	public void startMeasurements() {
		if (numOfMeasurements_ < 1) {
			numOfMeasurements_ = 1;
		}
	}

	public boolean measurementsTaken() {
		return numOfMeasurements_ > 0;
	}

	/**
	 * @return the number of (parallel) measurements aggregated in this
	 *         {@link AbstractStatistics}
	 */
	public int getNumberOfMeasurements() {
		return numOfMeasurements_;
	}

	/**
	 * Reset all measurements
	 */
	public void reset() {
		numOfMeasurements_ = 0;
	}

	public synchronized void add(AbstractStatistics stats) {
		numOfMeasurements_ += stats.numOfMeasurements_;
	}

}
