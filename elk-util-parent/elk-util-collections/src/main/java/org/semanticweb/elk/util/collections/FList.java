
package org.semanticweb.elk.util.collections;



import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple interface for lists as used in functional programming;
 * 
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <E>
 *            the elements contained in the list
 * 
 */
public interface FList<E> extends Iterable<E> {

	/**
	 * @return the first element in the list; it cannot be {@code null}
	 */
	E getHead();

	/**
	 * @return the remaining elements of this {@link FList} except for the first
	 *         one, or {@code null} if there are no such elements
	 */
	FList<E> getTail();

	public static class FListIterator<E> implements Iterator<E> {

		private FList<E> list_;

		public FListIterator(FList<E> list) {
			this.list_ = list;
		}

		@Override
		public boolean hasNext() {
			return list_ != null;
		}

		@Override
		public E next() {
			if (list_ == null)
				throw new NoSuchElementException();
			E result = list_.getHead();
			list_ = list_.getTail();
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"The list cannot be modified!");
		}

	}

	public static class Equality {

		public static boolean equals(FList<?> first, Object second) {
			if (second instanceof FList) {
				return equals(first, (FList<?>) second);
			}
			// else
			return false;
		}

		private static boolean equals(FList<?> first, FList<?> second) {
			// implementation without recursive call
			for (;;) {
				if (first == second) {
					return true;
				}
				// else
				if (first == null || second == null) {
					return false;
				}
				// else
				if (!first.getHead().equals(second.getHead()))
					return false;
				// else
				first = first.getTail();
				second = second.getTail();
			}

		}
	}

	public static class Hash {

		public static int hashCode(FList<?> list) {
			// compute has code like for a list
			int result = 0;
			for (;;) {
				if (list == null)
					return result;
				// else
				result = 31 * list.getHead().hashCode();
				list = list.getTail();
			}
		}
	}

}
