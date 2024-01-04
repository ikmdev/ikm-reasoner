
package org.semanticweb.elk.matching.conclusions;



abstract class AbstractIndexedAxiomMatch<P> extends AbstractConclusionMatch<P>
		implements IndexedAxiomMatch {

	AbstractIndexedAxiomMatch(P parent) {
		super(parent);
	}

	@Override
	public <O> O accept(ConclusionMatch.Visitor<O> visitor) {
		return accept((IndexedAxiomMatch.Visitor<O>) visitor);
	}

}
