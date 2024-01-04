
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link CachedIndexedClass} that represents {@code owl:Nothing}.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedOwlNothing extends CachedIndexedClass {

	public boolean occursPositively();

	public boolean addListener(ChangeListener listener);

	public boolean removeListener(ChangeListener listener);

	public interface ChangeListener {

		void positiveOccurrenceAppeared();

		void positiveOccurrenceDisappeared();

	}

}
