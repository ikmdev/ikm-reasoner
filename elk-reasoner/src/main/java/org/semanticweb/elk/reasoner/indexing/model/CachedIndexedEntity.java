
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.util.collections.entryset.Entry;

/**
 * 
 * A {@link ModifiableIndexedEntity} that can be used for memoization (caching).
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <T>
 *            the type of the {@link CachedIndexedEntity}
 * 
 */
public interface CachedIndexedEntity<T extends CachedIndexedEntity<T>>
		extends
			ModifiableIndexedEntity,
			CachedIndexedSubObject<T>,
			Entry<T, T> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				CachedIndexedClassEntity.Factory,
				CachedIndexedObjectProperty.Factory {

		// combined interface

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter
			extends
				CachedIndexedClassEntity.Filter,
				CachedIndexedObjectProperty.Filter {

		// combined interface

	}

}
