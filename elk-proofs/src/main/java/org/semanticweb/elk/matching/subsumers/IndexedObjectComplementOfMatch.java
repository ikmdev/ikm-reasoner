
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;

public class IndexedObjectComplementOfMatch
		extends AbstractSubsumerElkObjectMatch<ElkObjectComplementOf> {

	IndexedObjectComplementOfMatch(ElkObjectComplementOf value) {
		super(value);
	}

	@Override
	public <O> O accept(SubsumerElkObjectMatch.Visitor<O> visitor) {
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

		O visit(IndexedObjectComplementOfMatch match);

	}

}
