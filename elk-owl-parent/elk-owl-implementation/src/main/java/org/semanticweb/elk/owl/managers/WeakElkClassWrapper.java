
package org.semanticweb.elk.owl.managers;

import java.lang.ref.ReferenceQueue;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.util.hashing.HashGenerator;

class WeakElkClassWrapper extends WeakWrapper<ElkClass> {

	WeakElkClassWrapper(ElkClass referent,
			ReferenceQueue<? super ElkClass> q) {
		super(referent, q);
	}

	@Override
	protected int hashCode(ElkClass referent) {
		return HashGenerator.combinedHashCode("ElkClass", referent.getIri());
	}

	@Override
	protected boolean equal(ElkClass referent, Object obj) {
		if (obj instanceof ElkClass)
			return referent.getIri().equals(((ElkClass) obj).getIri());
		return false;
	}

}
