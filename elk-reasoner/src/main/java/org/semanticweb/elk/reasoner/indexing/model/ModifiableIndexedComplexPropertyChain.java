
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedComplexPropertyChain} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedComplexPropertyChain extends
		ModifiableIndexedPropertyChain, IndexedComplexPropertyChain {

	@Override
	ModifiableIndexedObjectProperty getFirstProperty();

	@Override
	ModifiableIndexedPropertyChain getSuffixChain();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedComplexPropertyChain getIndexedComplexPropertyChain(
				ModifiableIndexedObjectProperty leftProperty,
				ModifiableIndexedPropertyChain rightProperty);

	}
	
}
