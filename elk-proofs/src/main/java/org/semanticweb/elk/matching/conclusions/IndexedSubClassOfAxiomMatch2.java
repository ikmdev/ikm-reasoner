
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

public class IndexedSubClassOfAxiomMatch2
		extends
			AbstractIndexedAxiomMatch<IndexedSubClassOfAxiomMatch1> {

	private final ElkClassExpression subClassMatch_, superClassMatch_;

	IndexedSubClassOfAxiomMatch2(IndexedSubClassOfAxiomMatch1 parent,
			ElkClassExpression subClassMatch,
			ElkClassExpression superClassMatch) {
		super(parent);
		this.subClassMatch_ = subClassMatch;
		this.superClassMatch_ = superClassMatch;
	}

	public ElkClassExpression getSubClassMatch() {
		return subClassMatch_;
	}

	public ElkClassExpression getSuperClassMatch() {
		return superClassMatch_;
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

		IndexedSubClassOfAxiomMatch2 getIndexedSubClassOfAxiomMatch2(
				IndexedSubClassOfAxiomMatch1 parent,
				ElkClassExpression subClassMatch,
				ElkClassExpression superClassMatch);

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

		O visit(IndexedSubClassOfAxiomMatch2 conclusionMatch);

	}

}
