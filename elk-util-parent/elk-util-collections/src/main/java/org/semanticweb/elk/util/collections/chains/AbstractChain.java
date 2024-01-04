
package org.semanticweb.elk.util.collections.chains;

import java.util.Map;



/**
 * This class provides a skeletal implementation of the {@link Chain} interface
 * to minimize the effort required to implement this interface. Essentially, one
 * has to provide only the implementation of the {@link Link} interface.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            The type of elements in the chain.
 */
public abstract class AbstractChain<T extends ModifiableLink<T>> implements
		Chain<T> {

	@Override
	public <S extends T> S find(Matcher<? super T, S> matcher) {
		T candidate = next();
		for (;;) {
			if (candidate == null)
				return null;
			S match = matcher.match(candidate);
			if (match != null)
				return match;
			candidate = candidate.next();
		}
	}

	@Override
	public <S extends T> S getCreate(Matcher<? super T, S> matcher,
			ReferenceFactory<T, S> factory) {
		T candidate = next();
		for (;;) {
			if (candidate == null) {
				S result = factory.create(next());
				setNext(result);
				return result;
			}
			S match = matcher.match(candidate);
			if (match != null)
				return match;
			candidate = candidate.next();
		}
	}

	@Override
	public <S extends T> S remove(Matcher<? super T, S> matcher) {
		ModifiableLink<T> point = this;
		for (;;) {
			T next = point.next();
			if (next == null)
				return null;
			S match = matcher.match(next);
			if (match != null) {
				point.setNext(next.next());
				return match;
			}
			point = next;
		}
	}

	/**
	 * Creates a {@link Chain} view of the value associated with the given key
	 * in the given {@link Map}. The values of the map must be instances of the
	 * type that can be used in the {@link Chain} interface. All operations with
	 * the returned {@link Chain}, such as addition or removal, will be
	 * reflected accordingly in the corresponding value in the {@link Map}.
	 * 
	 * @param map
	 *            the {@link Map} that backs the data
	 * @param key
	 *            the key for which to return the {@link Chain} view of the data
	 * @return the {@link Chain} view of the data associated with key in map
	 */
	public static <K, T extends ModifiableLink<T>> Chain<T> getMapBackedChain(
			final Map<K, T> map, final K key) {
		return new AbstractChain<T>() {

			@Override
			public T next() {
				return map.get(key);
			}

			@Override
			public void setNext(T next) {
				if (next == null)
					map.remove(key);
				else
					map.put(key, next);
			}
		};
	}
}
