
package org.semanticweb.elk.matching.conclusions;



abstract class AbstractClassConclusionMatch<P>
		extends AbstractConclusionMatch<P> implements ClassConclusionMatch {

	AbstractClassConclusionMatch(P parent) {
		super(parent);
	}

	@Override
	public <O> O accept(ConclusionMatch.Visitor<O> visitor) {
		return accept((ClassConclusionMatch.Visitor<O>) visitor);
	}

}
