
package org.semanticweb.elk.util.collections;

import java.util.Iterator;

import org.liveontologies.puli.statistics.HasStats;

import com.google.common.base.Predicate;

/**
 * Keeps track of added elements and informs of which of these elements were
 * evicted.
 * 
 * @author Peter Skocovsky
 *
 * @param <E>
 *            The type of the elements.
 */
public interface Evictor<E> extends HasStats {

	/**
	 * Add the provided element.
	 * 
	 * @param element
	 *            The added element.
	 */
	void add(E element);

	/**
	 * Return elements that are evicted.
	 * 
	 * @return The evicted elements.
	 */
	Iterator<E> evict();

	/**
	 * Return elements that are evicted. Does not evict the elements for which
	 * {@code retain} returns {@code true}.
	 * 
	 * @param element
	 *            The added element.
	 * @param retain
	 *            A {@link Predicate} that is {@code true} for elements that
	 *            should be retained.
	 * @return The evicted elements.
	 */
	Iterator<E> evict(Predicate<E> retain);

	/**
	 * Add the provided element and return elements that were evicted.
	 * 
	 * @param element
	 *            The added element.
	 * @return The evicted elements.
	 */
	Iterator<E> addAndEvict(E element);

	/**
	 * Add the provided element and return elements that were evicted. Does not
	 * evict the elements for which {@code retain} returns {@code true}.
	 * 
	 * @param element
	 *            The added element.
	 * @param retain
	 *            A {@link Predicate} that is {@code true} for elements that
	 *            should be retained.
	 * @return The evicted elements.
	 */
	Iterator<E> addAndEvict(E element, Predicate<E> retain);

	public static interface Builder {
		<E> Evictor<E> build();
	}

}
