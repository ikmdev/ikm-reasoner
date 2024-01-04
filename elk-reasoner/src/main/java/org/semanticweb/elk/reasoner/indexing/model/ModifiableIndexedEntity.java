
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedEntity} that can be modified as a result of updating the
 * {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedEntity extends ModifiableIndexedSubObject,
		IndexedEntity {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ModifiableIndexedClassEntity.Factory,
				ModifiableIndexedObjectProperty.Factory {

		// combined interface

	}


}
