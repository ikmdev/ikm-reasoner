
package org.semanticweb.elk.matching.conclusions;

import java.util.List;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

public class DisjointSubsumerMatch2
		extends AbstractClassConclusionMatch<DisjointSubsumerMatch1> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	private final List<? extends ElkClassExpression> disjointExpressionsMatch_;

	DisjointSubsumerMatch2(DisjointSubsumerMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch,
			List<? extends ElkClassExpression> disjointExpressionsMatch) {
		super(parent);
		this.extendedDestinationMatch_ = extendedDestinationMatch;
		this.disjointExpressionsMatch_ = disjointExpressionsMatch;
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	public List<? extends ElkClassExpression> getDisjointExpressionsMatch() {
		return disjointExpressionsMatch_;
	}

	@Override
	public <O> O accept(ClassConclusionMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		DisjointSubsumerMatch2 getDisjointSubsumerMatch2(
				DisjointSubsumerMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch,
				List<? extends ElkClassExpression> disjointExpressionsMatch);

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

		O visit(DisjointSubsumerMatch2 conclusionMatch);

	}

}
