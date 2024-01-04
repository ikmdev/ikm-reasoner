
package org.semanticweb.elk.owl.managers;

import java.lang.ref.ReferenceQueue;

import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.util.hashing.HashGenerator;

class WeakElkObjectPropertyWrapper extends WeakWrapper<ElkObjectProperty> {

	WeakElkObjectPropertyWrapper(ElkObjectProperty referent,
			ReferenceQueue<? super ElkObjectProperty> q) {
		super(referent, q);
	}

	@Override
	protected int hashCode(ElkObjectProperty referent) {
		return HashGenerator.combinedHashCode("ElkObjectProperty", referent.getIri());
	}

	@Override
	protected boolean equal(ElkObjectProperty referent, Object obj) {
		if (obj instanceof ElkObjectProperty)
			return referent.getIri().equals(((ElkObjectProperty) obj).getIri());
		return false;
	}

}
