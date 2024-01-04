
package org.semanticweb.elk.owl.managers;

import java.lang.ref.ReferenceQueue;

import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.util.hashing.HashGenerator;

class WeakElkDataPropertyWrapper extends WeakWrapper<ElkDataProperty> {

	WeakElkDataPropertyWrapper(ElkDataProperty referent,
			ReferenceQueue<? super ElkDataProperty> q) {
		super(referent, q);
	}

	@Override
	protected int hashCode(ElkDataProperty referent) {
		return HashGenerator.combinedHashCode("ElkDataProperty", referent.getIri());
	}

	@Override
	protected boolean equal(ElkDataProperty referent, Object obj) {
		if (obj instanceof ElkDataProperty)
			return referent.getIri().equals(((ElkDataProperty) obj).getIri());
		return false;
	}

}
