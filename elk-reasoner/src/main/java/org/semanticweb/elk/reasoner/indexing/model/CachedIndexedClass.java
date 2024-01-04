
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClass;

/**
 * A {@link ModifiableIndexedClass} that can be used for memoization (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedClass extends ModifiableIndexedClass,
		CachedIndexedClassEntity<CachedIndexedClass> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {
		
		CachedIndexedClass getIndexedClass(ElkClass elkClass);
		
	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedClass filter(CachedIndexedClass element);

	}
			
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(ElkClass entity) {
			return combinedHashCode(CachedIndexedClass.class, entity.getIri());
		}

		public static CachedIndexedClass structuralEquals(
				CachedIndexedClass first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedClass) {
				CachedIndexedClass secondEntry = (CachedIndexedClass) second;
				if (first.getElkEntity().getIri()
						.equals(secondEntry.getElkEntity().getIri()))
					return secondEntry;
			}
			return null;
		}

	}
}
