
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedClassEntity} that can be modified as a result of updating
 * the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedClassEntity
		extends
			ModifiableIndexedClassExpression,
			ModifiableIndexedEntity,
			IndexedClassEntity {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ModifiableIndexedClass.Factory,
				ModifiableIndexedIndividual.Factory {

		// combined interface

	}

}
