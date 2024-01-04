
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;

public class IndexedDataHasValueMatch
		extends AbstractSubsumerElkObjectMatch<ElkDataHasValue> {

	IndexedDataHasValueMatch(ElkDataHasValue value) {
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

		O visit(IndexedDataHasValueMatch match);

	}

}
