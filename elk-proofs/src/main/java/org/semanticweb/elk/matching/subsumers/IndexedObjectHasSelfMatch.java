
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;

public class IndexedObjectHasSelfMatch
		extends AbstractSubsumerElkObjectMatch<ElkObjectHasSelf> {

	IndexedObjectHasSelfMatch(ElkObjectHasSelf value) {
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

		O visit(IndexedObjectHasSelfMatch match);

	}

}
