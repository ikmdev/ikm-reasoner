
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

/**
 * A {@link ModifiableIndexedObjectProperty} that can be used for memoization
 * (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedObjectProperty extends
		ModifiableIndexedObjectProperty,
		CachedIndexedPropertyChain<CachedIndexedObjectProperty>,
		CachedIndexedEntity<CachedIndexedObjectProperty> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedObjectProperty getIndexedObjectProperty(
				ElkObjectProperty elkObjectProperty);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedObjectProperty filter(CachedIndexedObjectProperty element);

	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(ElkObjectProperty entity) {
			return combinedHashCode(CachedIndexedObjectProperty.class,
					entity.getIri());
		}

		public static CachedIndexedObjectProperty structuralEquals(
				CachedIndexedObjectProperty first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedObjectProperty) {
				CachedIndexedObjectProperty secondEntry = (CachedIndexedObjectProperty) second;
				if (first.getElkEntity().getIri()
						.equals(secondEntry.getElkEntity().getIri()))
					return secondEntry;
			}
			// else
			return null;
		}

	}

}
