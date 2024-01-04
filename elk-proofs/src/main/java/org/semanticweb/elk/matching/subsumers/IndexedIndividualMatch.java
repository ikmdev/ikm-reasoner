
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkIndividual;

public class IndexedIndividualMatch
		extends AbstractIndexedClassEntityMatch<ElkIndividual> {

	IndexedIndividualMatch(ElkIndividual value) {
		super(value);
	}

	@Override
	public <O> O accept(IndexedClassEntityMatch.Visitor<O> visitor) {
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

		O visit(IndexedIndividualMatch match);

	}

}
