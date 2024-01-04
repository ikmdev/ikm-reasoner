
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedObject} than be modified as a result of updating the
 * {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedObject extends IndexedObject {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends				
				ModifiableIndexedAxiom.Factory,
				ModifiableIndexedSubObject.Factory {

		// combined interface

	}

}
