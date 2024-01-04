
package org.semanticweb.elk.matching.subsumers;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;



import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;

public class SubsumerSingletonObjectOneOfMatch
		extends AbstractSubsumerNonCanonicalMatch<ElkObjectOneOf> {

	SubsumerSingletonObjectOneOfMatch(ElkObjectOneOf value) {
		super(value);
		if (value.getIndividuals().size() != 1) {
			throw new IllegalArgumentException(
					"ElkObjectOneOf must be singleton: " + value);
		}
	}

	public ElkIndividual getMember() {
		return getValue().getIndividuals().get(0);
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

		O visit(SubsumerSingletonObjectOneOfMatch match);

	}

}
