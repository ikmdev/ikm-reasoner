
package org.semanticweb.elk.reasoner.indexing.model;



import java.util.List;

import org.semanticweb.elk.util.collections.entryset.Entry;

/**
 * A {@link ModifiableIndexedClassExpressionList} that can be used for
 * memoization (caching).
 * 
 * @author "Yevgeny Kazakov"
 */
public interface CachedIndexedClassExpressionList
		extends
			ModifiableIndexedClassExpressionList,
			CachedIndexedSubObject<CachedIndexedClassExpressionList>,
			Entry<CachedIndexedClassExpressionList, CachedIndexedClassExpressionList> {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		CachedIndexedClassExpressionList getIndexedClassExpressionList(
				List<? extends ModifiableIndexedClassExpression> members);

	}
	
	/**
	 * A filter for mapping objects
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Filter {

		CachedIndexedClassExpressionList filter(
				CachedIndexedClassExpressionList element);

	}
	
	static class Helper extends CachedIndexedObject.Helper {

		public static int structuralHashCode(
				List<? extends ModifiableIndexedClassExpression> members) {
			return combinedHashCode(CachedIndexedClassExpressionList.class,
					combinedHashCode(members));
		}

		public static CachedIndexedClassExpressionList structuralEquals(
				CachedIndexedClassExpressionList first, Object second) {
			if (first == second) {
				return first;
			}
			if (second instanceof CachedIndexedClassExpressionList) {
				CachedIndexedClassExpressionList secondEntry = (CachedIndexedClassExpressionList) second;
				if (first.getElements().equals(secondEntry.getElements()))
					return secondEntry;
			}
			// else
			return null;
		}

	}

}
