
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link CachedIndexedClass} that represents {@code owl:Thing}.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedOwlThing extends CachedIndexedClass {

	public boolean occursNegatively();

	public boolean addListener(ChangeListener listener);

	public boolean removeListener(ChangeListener listener);

	public interface ChangeListener {

		void negativeOccurrenceAppeared();

		void negativeOccurrenceDisappeared();

	}

}
