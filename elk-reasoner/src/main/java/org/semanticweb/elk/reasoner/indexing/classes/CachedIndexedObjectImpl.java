
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObject;
import org.semanticweb.elk.util.collections.entryset.Entry;
import org.semanticweb.elk.util.collections.entryset.EntryCollection;

/**
 * Implements {@link CachedIndexedObject} and {@link Entry} so that these
 * objects can be stored in {@link EntryCollection} together with other
 * elements.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of objects this object can be structurally equal to
 * 
 * @param <N>
 *            The type of the elements in the set where this entry is used
 * 
 */
abstract class CachedIndexedObjectImpl<T extends CachedIndexedObject<T> & Entry<T, N>, N>
		extends IndexedObjectImpl implements CachedIndexedObject<T>,
		Entry<T, N> {

	/**
	 * The reference to the next element
	 */
	private N next_;

	private final int structuralHash_;

	CachedIndexedObjectImpl(int structuralHash) {
		this.structuralHash_ = structuralHash;
	}

	@Override
	public final void setNext(N next) {
		this.next_ = next;
	}

	@Override
	public final N getNext() {
		return next_;
	}

	@Override
	public final int structuralHashCode() {
		return structuralHash_;
	}

}
