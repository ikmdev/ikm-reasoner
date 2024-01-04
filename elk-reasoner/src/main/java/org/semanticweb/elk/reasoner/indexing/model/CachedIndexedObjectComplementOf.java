
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedObjectComplementOf} that can be used for
 * memoization (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedObjectComplementOf extends
		ModifiableIndexedObjectComplementOf,
		CachedIndexedComplexClassExpression<CachedIndexedObjectComplementOf> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedObjectComplementOf getIndexedObjectComplementOf(
				ModifiableIndexedClassExpression negated);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedObjectComplementOf filter(
				CachedIndexedObjectComplementOf element);

	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(IndexedClassExpression negated) {
			return combinedHashCode(CachedIndexedObjectComplementOf.class,
					negated);
		}

		public static CachedIndexedObjectComplementOf structuralEquals(
				CachedIndexedObjectComplementOf first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedObjectComplementOf) {
				CachedIndexedObjectComplementOf secondEntry = (CachedIndexedObjectComplementOf) second;
				if (first.getNegated().equals(secondEntry.getNegated()))
					return secondEntry;
			}
			// else
			return null;
		}

	}

}
