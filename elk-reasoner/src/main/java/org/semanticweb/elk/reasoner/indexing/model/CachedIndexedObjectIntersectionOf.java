
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedObjectIntersectionOf} that can be used for
 * memoization (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedObjectIntersectionOf extends
		ModifiableIndexedObjectIntersectionOf,
		CachedIndexedComplexClassExpression<CachedIndexedObjectIntersectionOf> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedObjectIntersectionOf getIndexedObjectIntersectionOf(
				ModifiableIndexedClassExpression conjunctA,
				ModifiableIndexedClassExpression conjunctB);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedObjectIntersectionOf filter(
				CachedIndexedObjectIntersectionOf element);

	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(
				IndexedClassExpression firstConjunct,
				IndexedClassExpression secondConjunct) {
			return combinedHashCode(CachedIndexedObjectIntersectionOf.class,
					firstConjunct, secondConjunct);
		}

		public static CachedIndexedObjectIntersectionOf structuralEquals(
				CachedIndexedObjectIntersectionOf first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedObjectIntersectionOf) {
				CachedIndexedObjectIntersectionOf secondEntry = (CachedIndexedObjectIntersectionOf) second;
				if (first.getFirstConjunct().equals(
						secondEntry.getFirstConjunct())
						&& first.getSecondConjunct().equals(
								secondEntry.getSecondConjunct()))
					return secondEntry;
			}
			// else
			return null;
		}
	}

}
