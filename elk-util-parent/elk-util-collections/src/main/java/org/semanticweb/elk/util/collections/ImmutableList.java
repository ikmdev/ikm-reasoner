
package org.semanticweb.elk.util.collections;



import java.util.Iterator;

/**
 * A simple implementation of an {@link FList}.
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <E>
 */
public class ImmutableList<E> implements FList<E> {

	private final E head_;

	private final FList<E> tail_;

	public ImmutableList(E head, FList<E> tail) {
		if (head == null)
			throw new IllegalArgumentException("The head cannot be null!");
		this.head_ = head;
		this.tail_ = tail;
	}

	@Override
	public E getHead() {
		return head_;
	}

	@Override
	public FList<E> getTail() {
		return tail_;
	}

	@Override
	public Iterator<E> iterator() {
		return new FListIterator<E>(this);
	}
}
