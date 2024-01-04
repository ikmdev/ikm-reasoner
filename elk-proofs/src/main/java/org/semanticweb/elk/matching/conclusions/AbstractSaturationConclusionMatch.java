
package org.semanticweb.elk.matching.conclusions;



abstract class AbstractSaturationConclusionMatch<P> extends
		AbstractConclusionMatch<P> implements SaturationConclusionMatch {

	AbstractSaturationConclusionMatch(P parent) {
		super(parent);
	}

	@Override
	public <O> O accept(ConclusionMatch.Visitor<O> visitor) {
		return accept((SaturationConclusionMatch.Visitor<O>) visitor);
	}

}
