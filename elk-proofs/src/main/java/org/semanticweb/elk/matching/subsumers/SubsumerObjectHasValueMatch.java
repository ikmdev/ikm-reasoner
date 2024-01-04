
package org.semanticweb.elk.matching.subsumers;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch.Factory;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;



import org.semanticweb.elk.owl.interfaces.ElkObjectHasValue;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;

public class SubsumerObjectHasValueMatch
		extends AbstractIndexedObjectSomeValuesFromMatch<ElkObjectHasValue> {

	SubsumerObjectHasValueMatch(ElkObjectHasValue value) {
		super(value);
	}

	@Override
	public ElkObjectPropertyExpression getPropertyMatch() {
		return getValue().getProperty();
	}

	@Override
	public ElkIndividual getFillerMatch() {
		return getValue().getFiller();
	}

	@Override
	public IndexedContextRootMatch getFillerRootMatch(Factory factory) {
		return factory
				.getIndexedContextRootIndividualMatch(getValue().getFiller());
	}

	@Override
	public IndexedContextRootMatch getRangeRootMatch(Factory factory) {
		return factory.getIndexedContextRootIndividualMatch(getFillerMatch());
	}

	@Override
	public <O> O accept(IndexedObjectSomeValuesFromMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(SubsumerObjectHasValueMatch match);

	}

}
