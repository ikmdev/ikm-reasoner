
package org.semanticweb.elk.matching.root;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;

public class IndexedContextRootMatchBaseFactory
		implements IndexedContextRootMatch.Factory {

	@Override
	public IndexedContextRootClassExpressionMatch getIndexedContextRootClassExpressionMatch(
			ElkClassExpression match) {
		return new IndexedContextRootClassExpressionMatch(match);
	}

	@Override
	public IndexedContextRootIndividualMatch getIndexedContextRootIndividualMatch(
			ElkIndividual value) {
		return new IndexedContextRootIndividualMatch(value);
	}

}
