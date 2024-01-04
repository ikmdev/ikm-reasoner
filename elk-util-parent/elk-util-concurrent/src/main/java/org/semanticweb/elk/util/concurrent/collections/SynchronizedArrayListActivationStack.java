
package org.semanticweb.elk.util.concurrent.collections;



import java.util.ArrayList;

/**
 * A simple implementation of {@link ActivationStack} using an {@link ArrayList}
 * for storing elements; addition and removal of elements are synchronized.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <E>
 *            the type of elements in the stack
 */
public class SynchronizedArrayListActivationStack<E> implements
		ActivationStack<E> {

	/**
	 * The elements stored in this {@link ActivationStack}
	 */
	private ArrayList<E> elements_ = null;

	@Override
	public synchronized boolean push(E element) {
		if (element == null)
			throw new IllegalArgumentException(
					"Elements in the stack cannot be null");
		boolean result = false;
		if (elements_ == null) {
			elements_ = new ArrayList<E>(4);
			result = true;
		}
		elements_.add(element);
		return result;
	}

	@Override
	public synchronized E pop() {
		for (;;) {
			if (elements_ == null)
				return null;
			if (elements_.isEmpty()) {
				elements_ = null;
				return null;
			}
			return elements_.remove(elements_.size() - 1);
		}
	}

	@Override
	public E peek() {
		if (elements_ == null)
			return null;
		return elements_.get(elements_.size() - 1);
	}

}
