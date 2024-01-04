
package org.semanticweb.elk.matching.subsumers;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;



import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;

public class SubsumerSingletonObjectUnionOfMatch
		extends AbstractSubsumerNonCanonicalMatch<ElkObjectUnionOf> {

	SubsumerSingletonObjectUnionOfMatch(ElkObjectUnionOf value) {
		super(value);
		if (value.getClassExpressions().size() != 1) {
			throw new IllegalArgumentException(
					"ElkObjectUnionOf must be singleton: " + value);
		}
	}

	public ElkClassExpression getMember() {
		return getValue().getClassExpressions().get(0);
	}

	@Override
	public <O> O accept(SubsumerNonCanonicalMatch.Visitor<O> visitor) {
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

		O visit(SubsumerSingletonObjectUnionOfMatch match);

	}

}
