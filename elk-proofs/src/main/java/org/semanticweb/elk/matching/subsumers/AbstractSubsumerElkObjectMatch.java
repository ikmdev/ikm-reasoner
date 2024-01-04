
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObject;

abstract class AbstractSubsumerElkObjectMatch<V extends ElkObject>
		extends AbstractSubsumerMatch implements SubsumerElkObjectMatch {

	private final V value_;

	AbstractSubsumerElkObjectMatch(V value) {
		this.value_ = value;
	}

	@Override
	public V getValue() {
		return value_;
	}

	@Override
	public final <O> O accept(SubsumerMatch.Visitor<O> visitor) {
		return accept((SubsumerElkObjectMatch.Visitor<O>) visitor);
	}

}
