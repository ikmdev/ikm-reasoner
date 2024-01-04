
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedObjectHasSelf} that can be modified as a result of updating
 * the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedObjectHasSelf extends
		ModifiableIndexedClassExpression, IndexedObjectHasSelf {

	@Override
	ModifiableIndexedObjectProperty getProperty();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedObjectHasSelf getIndexedObjectHasSelf(
				ModifiableIndexedObjectProperty property);

	}
	
}
