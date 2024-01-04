
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;

/**
 * An {@link IndexedDataHasValue} that can be modified as a result of updating
 * the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedDataHasValue
		extends
			ModifiableIndexedClassExpression,
			IndexedDataHasValue {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedDataHasValue getIndexedDataHasValue(
				ElkDataHasValue elkDataHasValue);

	}

}
