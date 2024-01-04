
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;

public class SubsumerObjectOneOfMatch
		extends AbstractIndexedObjectUnionOfMatch<ElkObjectOneOf> {

	SubsumerObjectOneOfMatch(ElkObjectOneOf value) {
		super(value);
		if (value.getIndividuals().size() <= 1) {
			throw new IllegalArgumentException(
					"ElkObjectOneOf must have at least two individuals: "
							+ value);
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

		O visit(SubsumerObjectOneOfMatch match);

	}

}
