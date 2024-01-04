
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch.Factory;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;

public class SubsumerObjectSomeValuesFromMatch extends
		AbstractIndexedObjectSomeValuesFromMatch<ElkObjectSomeValuesFrom> {

	SubsumerObjectSomeValuesFromMatch(ElkObjectSomeValuesFrom value) {
		super(value);
	}

	@Override
	public ElkObjectPropertyExpression getPropertyMatch() {
		return getValue().getProperty();
	}

	@Override
	public ElkClassExpression getFillerMatch() {
		return getValue().getFiller();
	}

	@Override
	public IndexedContextRootMatch getFillerRootMatch(Factory factory) {
		return factory.getIndexedContextRootClassExpressionMatch(
				getValue().getFiller());
	}

	@Override
	public IndexedContextRootMatch getRangeRootMatch(Factory factory) {
		return factory
				.getIndexedContextRootClassExpressionMatch(getFillerMatch());
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

		O visit(SubsumerObjectSomeValuesFromMatch match);

	}

}
