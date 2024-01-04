
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObject;

abstract class AbstractSubsumerNonCanonicalMatch<V extends ElkObject> extends
		AbstractSubsumerElkObjectMatch<V> implements SubsumerNonCanonicalMatch {

	AbstractSubsumerNonCanonicalMatch(V value) {
		super(value);
	}

	@Override
	public final <O> O accept(SubsumerElkObjectMatch.Visitor<O> visitor) {
		return accept((SubsumerNonCanonicalMatch.Visitor<O>) visitor);
	}

}
