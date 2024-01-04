
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.util.collections.entryset.Entry;

/**
 * A non-atomic {@link CachedIndexedClassExpression}, that is, not an
 * {@link CachedIndexedClassEntity}
 *
 * @param <T>
 *            the type of the {@link CachedIndexedComplexClassExpression}
 */
public interface CachedIndexedComplexClassExpression<T extends CachedIndexedComplexClassExpression<T>>
		extends CachedIndexedClassExpression<T>,
		Entry<T, CachedIndexedComplexClassExpression<?>> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				CachedIndexedDataHasValue.Factory,
				CachedIndexedObjectComplementOf.Factory,
				CachedIndexedObjectHasSelf.Factory,
				CachedIndexedObjectIntersectionOf.Factory,
				CachedIndexedObjectSomeValuesFrom.Factory,
				CachedIndexedObjectUnionOf.Factory {
		
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
				CachedIndexedDataHasValue.Filter,
				CachedIndexedObjectComplementOf.Filter,
				CachedIndexedObjectHasSelf.Filter,
				CachedIndexedObjectIntersectionOf.Filter,
				CachedIndexedObjectSomeValuesFrom.Filter,
				CachedIndexedObjectUnionOf.Filter {

		// combined interface

	}


}
