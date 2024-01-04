
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;

/**
 * A {@link ModifiableIndexedIndividual} that can be used for memoization
 * (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedIndividual extends ModifiableIndexedIndividual,
		CachedIndexedClassExpression<CachedIndexedIndividual>,
		CachedIndexedClassEntity<CachedIndexedIndividual> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedIndividual getIndexedIndividual(
				ElkNamedIndividual elkNamedIndividual);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedIndividual filter(CachedIndexedIndividual element);

	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(ElkNamedIndividual entity) {
			return combinedHashCode(CachedIndexedIndividual.class,
					entity.getIri());
		}

		public static CachedIndexedIndividual structuralEquals(
				CachedIndexedIndividual first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedIndividual) {
				CachedIndexedIndividual secondEntry = (CachedIndexedIndividual) second;
				if (first.getElkEntity().getIri()
						.equals(secondEntry.getElkEntity().getIri()))
					return secondEntry;
			}
			// else
			return null;
		}

	}

}
