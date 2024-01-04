
package org.semanticweb.elk.reasoner.indexing.model;



import java.util.List;

/**
 * An {@link IndexedClassExpressionList} that can be modified as a result
 * of updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedClassExpressionList
		extends
			ModifiableIndexedSubObject,
			IndexedClassExpressionList {

	@Override
	List<? extends ModifiableIndexedClassExpression> getElements();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedClassExpressionList getIndexedClassExpressionList(
				List<? extends ModifiableIndexedClassExpression> members);

	}
	
}
