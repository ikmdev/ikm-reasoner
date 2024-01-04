
package org.semanticweb.elk.matching.conclusions;



abstract class AbstractObjectPropertyConclusionMatch<P> extends
		AbstractConclusionMatch<P> implements ObjectPropertyConclusionMatch {

	AbstractObjectPropertyConclusionMatch(P parent) {
		super(parent);
	}

	@Override
	public <O> O accept(ConclusionMatch.Visitor<O> visitor) {
		return accept((ObjectPropertyConclusionMatch.Visitor<O>) visitor);
	}

}
