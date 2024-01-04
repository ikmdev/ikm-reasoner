
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedSubObject} that can be used for memoization
 * (caching).
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <T>
 *            the type of the {@link CachedIndexedSubObject}
 */
public interface CachedIndexedSubObject<T extends CachedIndexedSubObject<T>>
		extends ModifiableIndexedSubObject, CachedIndexedObject<T> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends				
				CachedIndexedClassExpression.Factory,
				CachedIndexedClassExpressionList.Factory,
				CachedIndexedEntity.Factory,
				CachedIndexedPropertyChain.Factory {

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
				CachedIndexedClassExpression.Filter,
				CachedIndexedClassExpressionList.Filter,
				CachedIndexedEntity.Filter,
				CachedIndexedPropertyChain.Filter {

		// combined interface
		
	}

}
