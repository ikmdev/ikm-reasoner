
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.util.collections.entryset.Entry;

/**
 * A {@link ModifiableIndexedComplexPropertyChain} that can be used for
 * memoization (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedComplexPropertyChain
		extends
		ModifiableIndexedComplexPropertyChain,
		CachedIndexedPropertyChain<CachedIndexedComplexPropertyChain>,
		Entry<CachedIndexedComplexPropertyChain, CachedIndexedComplexPropertyChain> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedComplexPropertyChain getIndexedComplexPropertyChain(
				ModifiableIndexedObjectProperty leftProperty,
				ModifiableIndexedPropertyChain rightProperty);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {
		
		CachedIndexedComplexPropertyChain filter(
				CachedIndexedComplexPropertyChain element);
		
	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(
				IndexedObjectProperty leftProperty,
				IndexedPropertyChain rightProperty) {
			return combinedHashCode(CachedIndexedComplexPropertyChain.class,
					leftProperty, rightProperty);
		}

		public static CachedIndexedComplexPropertyChain structuralEquals(
				CachedIndexedComplexPropertyChain first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedComplexPropertyChain) {
				CachedIndexedComplexPropertyChain secondEntry = (CachedIndexedComplexPropertyChain) second;
				if (first.getFirstProperty().equals(
						secondEntry.getFirstProperty())
						&& first.getSuffixChain().equals(
								secondEntry.getSuffixChain()))
					return secondEntry;
			}
			// else
			return null;
		}

	}

}
