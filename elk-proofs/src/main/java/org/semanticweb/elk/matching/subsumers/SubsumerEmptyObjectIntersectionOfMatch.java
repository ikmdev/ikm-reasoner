
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;

public class SubsumerEmptyObjectIntersectionOfMatch
		extends AbstractSubsumerNonCanonicalMatch<ElkObjectIntersectionOf> {

	SubsumerEmptyObjectIntersectionOfMatch(ElkObjectIntersectionOf value) {
		super(value);
		if (!value.getClassExpressions().isEmpty()) {
			throw new IllegalArgumentException(
					"ElkObjectIntersectionOf must be empty: " + value);
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

		O visit(SubsumerEmptyObjectIntersectionOfMatch match);

	}

}
