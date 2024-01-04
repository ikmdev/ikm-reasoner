
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

abstract class AbstractIndexedObjectSomeValuesFromMatch<V extends ElkClassExpression>
		extends AbstractSubsumerElkObjectMatch<V>
		implements IndexedObjectSomeValuesFromMatch {

	AbstractIndexedObjectSomeValuesFromMatch(V value) {
		super(value);
	}

	@Override
	public final <O> O accept(SubsumerElkObjectMatch.Visitor<O> visitor) {
		return accept((IndexedObjectSomeValuesFromMatch.Visitor<O>) visitor);
	}

}
