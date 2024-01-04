
package org.semanticweb.elk.reasoner.saturation;



import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.OntologyIndex;

/**
 * A {@link SaturationState} backed by a map from {@link IndexedClassExpression}
 * s to {@link ExtendedContext}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <EC>
 *            the type of contexts maintained by this {@link MapSaturationState}
 */
public class MapSaturationState<EC extends ExtendedContext>
		extends AbstractSaturationState<EC> {

	private final ConcurrentHashMap<IndexedContextRoot, EC> contextAssignment_;

	public MapSaturationState(OntologyIndex index, ContextFactory<EC> factory,
			int expectedSize) {
		super(index, factory);
		this.contextAssignment_ = new ConcurrentHashMap<IndexedContextRoot, EC>(
				expectedSize);
	}

	public MapSaturationState(OntologyIndex index, ContextFactory<EC> factory) {
		super(index, factory);
		this.contextAssignment_ = new ConcurrentHashMap<IndexedContextRoot, EC>(
				index.getClassExpressions().size());
	}

	@Override
	public Collection<EC> getContexts() {
		return contextAssignment_.values();
	}

	@Override
	public EC getContext(IndexedContextRoot root) {
		return contextAssignment_.get(root);
	}

	@Override
	void resetContexts() {
		contextAssignment_.clear();
		for (int i = 0; i < getChangeListenerCount(); i++) {
			getChangeListener(i).contextsClear();
		}
	}

	@Override
	EC setIfAbsent(EC context) {
		EC previous = contextAssignment_.putIfAbsent(context.getRoot(),
				context);
		if (previous == null) {
			for (int i = 0; i < getChangeListenerCount(); i++) {
				getChangeListener(i).contextAddition(context);
			}
		}
		return previous;
	}

}
