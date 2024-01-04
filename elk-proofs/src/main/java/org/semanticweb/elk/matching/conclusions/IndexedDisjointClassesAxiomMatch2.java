
package org.semanticweb.elk.matching.conclusions;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

public class IndexedDisjointClassesAxiomMatch2
		extends
			AbstractIndexedAxiomMatch<IndexedDisjointClassesAxiomMatch1> {

	private final List<? extends ElkClassExpression> memberMatches_;

	IndexedDisjointClassesAxiomMatch2(IndexedDisjointClassesAxiomMatch1 parent,
			List<? extends ElkClassExpression> memberMatches) {
		super(parent);
		this.memberMatches_ = memberMatches;
	}

	public List<? extends ElkClassExpression> getMemberMatches() {
		return memberMatches_;
	}

	@Override
	public <O> O accept(IndexedAxiomMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		IndexedDisjointClassesAxiomMatch2 getIndexedDisjointClassesAxiomMatch2(
				IndexedDisjointClassesAxiomMatch1 parent,
				List<? extends ElkClassExpression> memberMatches);

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

		O visit(IndexedDisjointClassesAxiomMatch2 conclusionMatch);

	}

}
