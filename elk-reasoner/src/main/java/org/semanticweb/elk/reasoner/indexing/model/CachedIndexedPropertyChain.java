
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedPropertyChain} that can be used for memoization
 * (caching).
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <T>
 *            the type of the {@link CachedIndexedPropertyChain}
 */
public interface CachedIndexedPropertyChain<T extends CachedIndexedPropertyChain<T>>
		extends ModifiableIndexedPropertyChain, CachedIndexedSubObject<T> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				CachedIndexedComplexPropertyChain.Factory,
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
				CachedIndexedComplexPropertyChain.Filter,
				CachedIndexedObjectProperty.Filter {

		// combined interface

	}
	
	T accept(Filter filter);		

}
