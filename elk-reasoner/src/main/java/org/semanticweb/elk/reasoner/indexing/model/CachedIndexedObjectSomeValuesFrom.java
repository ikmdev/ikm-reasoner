
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * A {@link ModifiableIndexedObjectSomeValuesFrom} that can be used for
 * memoization (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedObjectSomeValuesFrom extends
		ModifiableIndexedObjectSomeValuesFrom,
		CachedIndexedComplexClassExpression<CachedIndexedObjectSomeValuesFrom> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedObjectSomeValuesFrom getIndexedObjectSomeValuesFrom(
				ModifiableIndexedObjectProperty property,
				ModifiableIndexedClassExpression filler);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedObjectSomeValuesFrom filter(
				CachedIndexedObjectSomeValuesFrom element);

	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(IndexedObjectProperty property,
				IndexedClassExpression filler) {
			return combinedHashCode(CachedIndexedObjectSomeValuesFrom.class,
					property, filler);
		}

		public static CachedIndexedObjectSomeValuesFrom structuralEquals(
				CachedIndexedObjectSomeValuesFrom first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedObjectSomeValuesFrom) {
				CachedIndexedObjectSomeValuesFrom secondEntry = (CachedIndexedObjectSomeValuesFrom) second;
				if (first.getProperty().equals(secondEntry.getProperty())
						&& first.getFiller().equals(
								secondEntry.getFiller()))
					return secondEntry;
			}
			// else
			return null;
		}

	}

}
