
package org.semanticweb.elk.reasoner.saturation;



import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.OntologyIndex;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link SaturationState} in which {@link Context}s are assigned to
 * {@link IndexedClassExpression}s by references.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
class ReferenceSaturationState
		extends AbstractSaturationState<ExtendedContext> {

	// the number of contexts created by this SaturationState
	AtomicInteger contextCount = new AtomicInteger(0);

	/**
	 * 
	 * @param index
	 */
	public ReferenceSaturationState(OntologyIndex index) {
		super(index, new MainContextFactory());
	}

	@Override
	public Collection<ExtendedContext> getContexts() {
		return new AbstractCollection<ExtendedContext>() {

			@Override
			public Iterator<ExtendedContext> iterator() {
				return new Iterator<ExtendedContext>() {

					Iterator<? extends IndexedClassExpression> ices = ontologyIndex
							.getClassExpressions().iterator();

					ExtendedContext nextContext;
					ExtendedContext nextFillerContext;

					{
						seekNext();
					}

					void seekNext() {
						while (ices.hasNext()) {
							IndexedClassExpression nextIce = ices.next();
							nextContext = nextIce.getContext();
							if (nextIce instanceof IndexedObjectSomeValuesFrom) {
								nextFillerContext = ((IndexedObjectSomeValuesFrom) nextIce)
										.getRangeFiller().getContext();
								if (nextFillerContext != null)
									return;
							}
							if (nextContext != null)
								return;
						}
					}

					@Override
					public boolean hasNext() {
						return (nextContext != null
								|| nextFillerContext != null);
					}

					@Override
					public ExtendedContext next() {
						ExtendedContext result;
						if (nextContext != null) {
							result = nextContext;
							nextContext = null;
						} else if (nextFillerContext != null) {
							result = nextFillerContext;
							nextFillerContext = null;
						} else
							throw new NoSuchElementException("No next context");
						if (!hasNext())
							seekNext();
						return result;
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException(
								"Removal not supported");
					}

				};
			}

			@Override
			public boolean isEmpty() {
				return !iterator().hasNext();
			}

			@Override
			public int size() {
				return contextCount.get();
			}

		};
	}

	@Override
	public ExtendedContext getContext(IndexedContextRoot root) {
		return root.getContext();
	}

	@Override
	void resetContexts() {
		if (contextCount.get() == 0)
			// everything is already done
			return;
		// else
		for (ExtendedContext context : getContexts()) {
			context.getRoot().resetContext();			
		}
		contextCount.set(0);
		for (int i = 0; i < getChangeListenerCount(); i++) {
			getChangeListener(i).contextsClear();
		}
	}

	@Override
	ExtendedContext setIfAbsent(ExtendedContext context) {
		ExtendedContext previous = context.getRoot()
				.setContextIfAbsent(context);
		if (previous == null) {
			contextCount.incrementAndGet();
			for (int i = 0; i < getChangeListenerCount(); i++) {
				getChangeListener(i).contextAddition(context);
			}
		}
		return previous;
	}

}
