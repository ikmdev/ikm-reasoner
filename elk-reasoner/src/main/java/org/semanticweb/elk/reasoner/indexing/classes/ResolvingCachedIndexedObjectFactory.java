
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedSubObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectCache;



/**
 * A {@link CachedIndexedObject.Factory} which can only create objects present
 * in the provided {@link ModifiableIndexedObjectCache}. If there is no
 * structurally equivalent object to the one that should be constructed,
 * {@code null} is returned.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
class ResolvingCachedIndexedObjectFactory
		extends DelegatingCachedIndexedObjectFactory {

	private final ModifiableIndexedObjectCache cache_;

	public ResolvingCachedIndexedObjectFactory(
			CachedIndexedObject.Factory baseFactory,
			ModifiableIndexedObjectCache cache) {
		super(baseFactory);
		this.cache_ = cache;
	}

	@Override
	<T extends CachedIndexedSubObject<T>> T filter(T input) {
		if (input == null) {
			return null;
		}
		// else
		return cache_.resolve(input);
	}

}
