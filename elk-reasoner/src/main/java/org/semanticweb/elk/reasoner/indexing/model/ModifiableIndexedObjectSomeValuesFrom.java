
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedObjectSomeValuesFrom} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedObjectSomeValuesFrom extends
		ModifiableIndexedClassExpression, IndexedObjectSomeValuesFrom {

	@Override
	ModifiableIndexedObjectProperty getProperty();

	@Override
	ModifiableIndexedRangeFiller getRangeFiller();
	
	@Override
	ModifiableIndexedClassExpression getFiller();
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedObjectSomeValuesFrom getIndexedObjectSomeValuesFrom(
				ModifiableIndexedObjectProperty property,
				ModifiableIndexedClassExpression fillerConcept);

	}

}
