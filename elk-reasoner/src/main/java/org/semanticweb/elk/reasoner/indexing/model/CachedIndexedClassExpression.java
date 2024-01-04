
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedClassExpression} that can be used for memoization
 * (caching).
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <T>
 *            the type of the {@link CachedIndexedClassExpression}
 */
public interface CachedIndexedClassExpression<T extends CachedIndexedClassExpression<T>>
		extends CachedIndexedSubObject<T>, ModifiableIndexedClassExpression {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends				
				CachedIndexedClassEntity.Factory,
				CachedIndexedComplexClassExpression.Factory,
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
				CachedIndexedClassEntity.Filter,
				CachedIndexedComplexClassExpression.Filter,
				CachedIndexedIndividual.Filter {

		// combined interface

	}

	
	T accept(Filter filter);

}
