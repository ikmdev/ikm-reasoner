
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;

public class SubsumerEmptyObjectOneOfMatch
		extends AbstractSubsumerNonCanonicalMatch<ElkObjectOneOf> {

	SubsumerEmptyObjectOneOfMatch(ElkObjectOneOf value) {
		super(value);
		if (!value.getIndividuals().isEmpty()) {
			throw new IllegalArgumentException(
					"ElkObjectOneOf must be empty: " + value);
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

		O visit(SubsumerEmptyObjectOneOfMatch match);

	}

}
