
package org.semanticweb.elk.reasoner.completeness;



/**
 * An object that can provide information about the number of occurrences for
 * {@link Feature}s
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface OccurrenceCounter {

	/**
	 * @param occurrence
	 * @return the number of times a given {@link Feature} appears
	 */
	public int getOccurrenceCount(Feature occurrence);

}
