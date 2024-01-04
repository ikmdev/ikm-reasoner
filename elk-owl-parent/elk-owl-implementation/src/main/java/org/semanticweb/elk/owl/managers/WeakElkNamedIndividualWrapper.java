
package org.semanticweb.elk.owl.managers;

import java.lang.ref.ReferenceQueue;

import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.util.hashing.HashGenerator;

class WeakElkNamedIndividualWrapper extends WeakWrapper<ElkNamedIndividual> {

	WeakElkNamedIndividualWrapper(ElkNamedIndividual referent,
			ReferenceQueue<? super ElkNamedIndividual> q) {
		super(referent, q);
	}

	@Override
	protected int hashCode(ElkNamedIndividual referent) {
		return HashGenerator.combinedHashCode("ElkNamedIndividual", referent.getIri());
	}

	@Override
	protected boolean equal(ElkNamedIndividual referent, Object obj) {
		if (obj instanceof ElkNamedIndividual)
			return referent.getIri().equals(((ElkNamedIndividual) obj).getIri());
		return false;
	}

}
