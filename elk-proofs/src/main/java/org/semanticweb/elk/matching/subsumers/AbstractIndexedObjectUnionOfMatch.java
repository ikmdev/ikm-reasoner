
package org.semanticweb.elk.matching.subsumers;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;



abstract class AbstractIndexedObjectUnionOfMatch<V extends ElkClassExpression>
		extends AbstractSubsumerElkObjectMatch<V>
		implements IndexedObjectUnionOfMatch {

	AbstractIndexedObjectUnionOfMatch(V value) {
		super(value);
	}

	@Override
	public final <O> O accept(SubsumerElkObjectMatch.Visitor<O> visitor) {
		return accept((IndexedObjectUnionOfMatch.Visitor<O>) visitor);
	}

}
