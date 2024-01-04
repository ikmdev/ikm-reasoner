
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedRangeFiller} that can be modified as a result of updating
 * the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedRangeFiller extends IndexedRangeFiller {

	@Override
	ModifiableIndexedObjectProperty getProperty();

	@Override
	ModifiableIndexedClassExpression getFiller();

}
