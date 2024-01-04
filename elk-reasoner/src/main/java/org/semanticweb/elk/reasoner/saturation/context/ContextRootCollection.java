
package org.semanticweb.elk.reasoner.saturation.context;



import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;

/**
 * A collection of {@link IndexedContextRoot}s that are roots of the given
 * collection of {@link Context}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ContextRootCollection extends
		AbstractCollection<IndexedContextRoot> {

	private final Collection<? extends Context> contexts_;

	public ContextRootCollection(Collection<? extends Context> contexts) {
		this.contexts_ = contexts;
	}

	@Override
	public Iterator<IndexedContextRoot> iterator() {
		return new Iterator<IndexedContextRoot>() {

			private final Iterator<? extends Context> iter_ = contexts_
					.iterator();

			@Override
			public boolean hasNext() {
				return iter_.hasNext();
			}

			@Override
			public IndexedContextRoot next() {
				return iter_.next().getRoot();
			}

			@Override
			public void remove() {
				iter_.remove();
			}

		};
	}

	@Override
	public int size() {
		return contexts_.size();
	}

}
