
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;

public class SubsumerEmptyObjectUnionOfMatch
		extends AbstractSubsumerNonCanonicalMatch<ElkObjectUnionOf> {

	SubsumerEmptyObjectUnionOfMatch(ElkObjectUnionOf value) {
		super(value);
		if (!value.getClassExpressions().isEmpty()) {
			throw new IllegalArgumentException(
					"ElkObjectUnionOf must be empty: " + value);
		}
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

		O visit(SubsumerEmptyObjectUnionOfMatch match);

	}

}
