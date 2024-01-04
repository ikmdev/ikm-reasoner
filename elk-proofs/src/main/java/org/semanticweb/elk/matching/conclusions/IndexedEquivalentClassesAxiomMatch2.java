
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

public class IndexedEquivalentClassesAxiomMatch2
		extends AbstractIndexedAxiomMatch<IndexedEquivalentClassesAxiomMatch1> {

	private final ElkClassExpression firstMemberMatch_;

	private final ElkClassExpression secondMemberMatch_;

	IndexedEquivalentClassesAxiomMatch2(
			IndexedEquivalentClassesAxiomMatch1 parent,
			ElkClassExpression firstMemberMatch,
			ElkClassExpression secondMemberMatch) {
		super(parent);
		this.firstMemberMatch_ = firstMemberMatch;
		this.secondMemberMatch_ = secondMemberMatch;
	}

	public ElkClassExpression getFirstMemberMatch() {
		return firstMemberMatch_;
	}

	public ElkClassExpression getSecondMemberMatch() {
		return secondMemberMatch_;
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

		IndexedEquivalentClassesAxiomMatch2 getIndexedEquivalentClassesAxiomMatch2(
				IndexedEquivalentClassesAxiomMatch1 parent,
				ElkClassExpression firstMemberMatch,
				ElkClassExpression secondMemberMatch);

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

		O visit(IndexedEquivalentClassesAxiomMatch2 conclusionMatch);

	}

}
