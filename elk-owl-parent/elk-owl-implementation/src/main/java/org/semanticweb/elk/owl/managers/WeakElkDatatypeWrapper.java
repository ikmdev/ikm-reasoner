
package org.semanticweb.elk.owl.managers;

import java.lang.ref.ReferenceQueue;

import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.util.hashing.HashGenerator;

class WeakElkDatatypeWrapper extends WeakWrapper<ElkDatatype> {

	WeakElkDatatypeWrapper(ElkDatatype referent,
			ReferenceQueue<? super ElkDatatype> q) {
		super(referent, q);
	}

	@Override
	protected int hashCode(ElkDatatype referent) {
		return HashGenerator.combinedHashCode("ElkDatatype", referent.getIri());
	}

	@Override
	protected boolean equal(ElkDatatype referent, Object obj) {
		if (obj instanceof ElkDatatype)
			return referent.getIri().equals(((ElkDatatype) obj).getIri());
		return false;
	}

}
