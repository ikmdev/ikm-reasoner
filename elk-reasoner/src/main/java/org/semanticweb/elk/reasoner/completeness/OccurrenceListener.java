
package org.semanticweb.elk.reasoner.completeness;

/**
 * Notified when the number of occurrences for a {@link Feature} has changed.
 * 
 * @author Peter Skocovsky
 * @author Yevgeny Kazakov
 */
public interface OccurrenceListener {

	/**
	 * Is triggered when the number of occurrences of a given {@link Feature} is
	 * changed by the given increment
	 * 
	 * @param feature
	 * @param increment
	 */
	public void occurrenceChanged(Feature feature, int increment);

}
