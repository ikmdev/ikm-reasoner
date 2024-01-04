
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObject;

abstract class AbstractIndexedClassEntityMatch<V extends ElkObject> extends
		AbstractSubsumerElkObjectMatch<V> implements IndexedClassEntityMatch {

	AbstractIndexedClassEntityMatch(V value) {
		super(value);
	}

	@Override
	public <O> O accept(SubsumerElkObjectMatch.Visitor<O> visitor) {
		return accept((IndexedClassEntityMatch.Visitor<O>) visitor);
	}

}
