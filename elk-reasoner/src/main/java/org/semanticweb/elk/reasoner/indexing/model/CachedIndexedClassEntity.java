
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedClassEntity} that can be used for memoization
 * (caching).
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <T>
 *            the type of the {@link CachedIndexedClassEntity}
 */
public interface CachedIndexedClassEntity<T extends CachedIndexedClassEntity<T>>
		extends ModifiableIndexedClassEntity, CachedIndexedClassExpression<T>,
		CachedIndexedEntity<T> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				CachedIndexedClass.Factory,
				CachedIndexedIndividual.Factory {

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
				CachedIndexedClass.Filter,
				CachedIndexedIndividual.Filter {

		// combined interface
		
	}
}
