
package org.semanticweb.elk.util.concurrent.collections;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A thread-safe implementation of stack based on the non-blocking Treiber's
 * Algorithm (Treiber, 1986). The implementation allows to check when the pushed
 * element is the first element in the stack. This stack does not allow storing
 * {@code null} values.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <E>
 *            the type of elements in the stack
 */
public class ConcurrentLinkedActivationStack<E> implements ActivationStack<E> {

	private final AtomicReference<Node<E>> top_ = new AtomicReference<Node<E>>();

	/**
	 * a special dummy node used to mark the end of the stack after it has been
	 * activated
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Node<?> dummyNode = new Node(null);

	@Override
	@SuppressWarnings("unchecked")
	public boolean push(E element) {
		if (element == null)
			throw new IllegalArgumentException(
					"Elements in the stack cannot be null");
		Node<E> newHead = new Node<E>(element);
		Node<E> oldHead;
		for (;;) {
			oldHead = top_.get();
			if (oldHead == null)
				newHead.next = (Node<E>) dummyNode;
			else
				newHead.next = oldHead;
			if (top_.compareAndSet(oldHead, newHead)) {
				if (oldHead == null)
					return true;
				// else
				return false;
			}
		}
	}

	@Override
	public E peek() {
		Node<E> head = top_.get();
		if (head == null)
			return null;
		return head.item;
	}

	@Override
	public E pop() {
		for (;;) {
			Node<E> oldHead = top_.get();
			Node<E> newHead;
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
			if (top_.compareAndSet(oldHead, newHead))
				return oldHead.item;
		}
	}

	private static class Node<T> {
		public final T item;
		public Node<T> next;

		public Node(T item) {
			this.item = item;
		}
	}

}
