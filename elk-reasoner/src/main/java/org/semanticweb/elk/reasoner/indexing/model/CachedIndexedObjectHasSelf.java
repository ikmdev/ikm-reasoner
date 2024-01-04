
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedObjectHasSelf} that can be used for memoization
 * (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedObjectHasSelf extends
		ModifiableIndexedObjectHasSelf,
		CachedIndexedComplexClassExpression<CachedIndexedObjectHasSelf> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedObjectHasSelf getIndexedObjectHasSelf(
				ModifiableIndexedObjectProperty property);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedObjectHasSelf filter(CachedIndexedObjectHasSelf element);

	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(IndexedObjectProperty property) {
			return combinedHashCode(CachedIndexedObjectHasSelf.class, property);
		}

		public static CachedIndexedObjectHasSelf structuralEquals(
				CachedIndexedObjectHasSelf first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedObjectHasSelf) {
				CachedIndexedObjectHasSelf secondEntry = (CachedIndexedObjectHasSelf) second;
				if (first.getProperty().equals(secondEntry.getProperty()))
					return secondEntry;
			}
			// else
			return null;
		}

	}

}
