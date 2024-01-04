
package org.semanticweb.elk.reasoner.completeness;



import java.util.EnumMap;

/**
 * An object that keeps track of the number occurrences of {@link Feature}s
 * 
 * @author Yevgeny Kazakov
 */
public class OccurrenceRegistry extends EnumMap<Feature, Integer>
		implements OccurrenceCounter, OccurrenceListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8085426088043106502L;

	public OccurrenceRegistry() {
		super(Feature.class);
	}

	@Override
	public int getOccurrenceCount(Feature occurrence) {
		Integer result = get(occurrence);
		if (result == null) {
			return 0;
		}
		// else
		return result;
	}

	@Override
	public void occurrenceChanged(Feature occurrence, int increment) {
		Integer noOccurrences = getOccurrenceCount(occurrence);
		noOccurrences += increment;
		if (noOccurrences == 0) {
			remove(occurrence);
		} else {
			put(occurrence, noOccurrences);
		}
	}

}
