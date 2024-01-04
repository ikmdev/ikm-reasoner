
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedObject;

/**
 * Implements {@link IndexedObject}.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
abstract class IndexedObjectImpl implements IndexedObject {

	@Override
	public final String toString() {
		return IndexedObjectPrinter.toString(this);
	}

}
