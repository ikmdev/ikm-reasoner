
package org.semanticweb.elk.reasoner.taxonomy.impl;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.reasoner.taxonomy.model.NodeStore;

public abstract class AbstractNodeStore<T, N extends Node<T>>
		implements NodeStore<T, N> {

	/** The listeners notified about the changes. */
	protected final List<Listener<T>> listeners_;

	public AbstractNodeStore() {
		this.listeners_ = new ArrayList<Listener<T>>();
	}

	@Override
	public boolean addListener(final Listener<T> listener) {
		return listeners_.add(listener);
	}

	@Override
	public boolean removeListener(final Listener<T> listener) {
		return listeners_.remove(listener);
	}

	protected void fireMemberForNodeAppeared(final Node<T> node) {
		for (final Listener<T> listener : listeners_) {
			for (final T member : node) {
				listener.memberForNodeAppeared(member, node);
			}
		}
	}

	protected void fireMemberForNodeDisappeared(final Node<T> node) {
		for (final Listener<T> listener : listeners_) {
			for (final T member : node) {
				listener.memberForNodeDisappeared(member, node);
			}
		}
	}

}
