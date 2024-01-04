
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;

public class SubsumerObjectUnionOfMatch
		extends AbstractIndexedObjectUnionOfMatch<ElkObjectUnionOf> {

	SubsumerObjectUnionOfMatch(ElkObjectUnionOf value) {
		super(value);
		if (value.getClassExpressions().size() <= 1) {
			throw new IllegalArgumentException(
					"ElkObjectUnionOf must have at lest two members: " + value);
		}
	}

	@Override
	public <O> O accept(IndexedObjectUnionOfMatch.Visitor<O> visitor) {
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

		O visit(SubsumerObjectUnionOfMatch match);

	}

}
