
package org.semanticweb.elk.util.collections;

import java.util.Iterator;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public abstract class AbstractEvictor<E> implements Evictor<E> {

	@Override
	public Iterator<E> evict() {
		return evict(Predicates.<E> alwaysFalse());
	}

	@Override
	public Iterator<E> addAndEvict(final E element) {
		return addAndEvict(element, Predicates.<E> alwaysFalse());
	}

	@Override
	public Iterator<E> addAndEvict(final E element, final Predicate<E> retain) {
		add(element);
		return evict(retain);
	}

	protected Object stats = null;

	@Override
	public Object getStats() {
		return stats;
	}

}
