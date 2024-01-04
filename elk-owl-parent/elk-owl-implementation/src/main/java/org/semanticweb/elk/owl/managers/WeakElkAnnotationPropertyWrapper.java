
package org.semanticweb.elk.owl.managers;

import java.lang.ref.ReferenceQueue;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.util.hashing.HashGenerator;

class WeakElkAnnotationPropertyWrapper extends WeakWrapper<ElkAnnotationProperty> {

	WeakElkAnnotationPropertyWrapper(ElkAnnotationProperty referent,
			ReferenceQueue<? super ElkAnnotationProperty> q) {
		super(referent, q);
	}

	@Override
	protected int hashCode(ElkAnnotationProperty referent) {
		return HashGenerator.combinedHashCode("ElkAnnotationProperty", referent.getIri());
	}

	@Override
	protected boolean equal(ElkAnnotationProperty referent, Object obj) {
		if (obj instanceof ElkAnnotationProperty)
			return referent.getIri().equals(((ElkAnnotationProperty) obj).getIri());
		return false;
	}

}
