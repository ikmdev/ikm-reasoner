
package org.semanticweb.elk.util.collections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Represents a {@link Collection} view consisting of elements from a given
 * {@link Collection} without elements from the given {@link Set}. The given
 * {@link Collection} and {@link Set} are not modified as a result of this
 * operation. The resulting {@link Collection} does not support addition or
 * removal of elements; if attempted, an {@link UnsupportedOperationException}
 * will be thrown.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <E>
 *            the type of elements in the collection
 */
public class LazyCollectionMinusSet<E> extends AbstractCollection<E> {

	private final Collection<? extends E> collection_;
	private final Set<? extends E> set_;

	public LazyCollectionMinusSet(final Collection<? extends E> collection,
			final Set<? extends E> set) {
		collection_ = collection;
		set_ = set;
	}

	@Override
	public boolean isEmpty() {
		return set_.containsAll(collection_);
	}

	@Override
	public boolean contains(Object o) {
		return collection_.contains(o) && !set_.contains(o);
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private final Iterator<? extends E> iter_ = collection_.iterator();
			private E next_ = null;

			@Override
			public boolean hasNext() {

				while (next_ == null && iter_.hasNext()) {
					E elem = iter_.next();

					next_ = set_.contains(elem) ? null : elem;
				}

				return next_ != null;
			}

			@Override
			public E next() {
				if (next_ != null) {
					return giveAway();
				} else if (hasNext()) {
					return giveAway();
				} else {
					throw new NoSuchElementException();
				}
			}

			private E giveAway() {
				E elem = next_;

				next_ = null;

				return elem;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

	@Override
	public int size() {
		return collection_.size() - set_.size();
	}
}
