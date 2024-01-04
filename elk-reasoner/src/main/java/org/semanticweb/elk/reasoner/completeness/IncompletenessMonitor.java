
package org.semanticweb.elk.reasoner.completeness;

import org.slf4j.Logger;

/**
 * Keeps of properties that can cause incompleteness of a reasoning task, for
 * example, occurrence of some unsupported constructors in the ontology, or
 * violation of some global restrictions.
 * 
 * @author Peter Skocovsky
 * @author Yevgeny Kazakov
 */
public interface IncompletenessMonitor {

	/**
	 * @return {@code true} if potential problem that can result in
	 *         incompleteness has been detected by this
	 *         {@link IncompletenessMonitor}
	 */
	boolean isIncompletenessDetected();

	/**
	 * Produces log status messages explaining possible incompleteness of the
	 * results. The messages are incremental, that is, only changes compared to
	 * the previous call of {@link #logStatus(Logger)} are printed. In
	 * particular, if this method is called two times immediately after another,
	 * then the second call will not produce any messages. This has been done to
	 * minimize log pollution.
	 * 
	 * @param logger
	 *                   the logger using which the messages are printed
	 */
	void logStatus(Logger logger);

	/**
	 * @return {@code true} if calling {@link #logStatus(Logger)} would produce
	 *         at least one log message and {@code false} otherwise.
	 * @param logger
	 *            the logger for which change of status is checked
	 */
	boolean isStatusChanged(Logger logger);

}
