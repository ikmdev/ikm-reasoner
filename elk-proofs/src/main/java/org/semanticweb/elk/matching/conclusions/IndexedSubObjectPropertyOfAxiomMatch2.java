
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;

public class IndexedSubObjectPropertyOfAxiomMatch2 extends
		AbstractIndexedAxiomMatch<IndexedSubObjectPropertyOfAxiomMatch1> {

	private final ElkSubObjectPropertyExpression subPropertyChainMatch_;

	private final ElkObjectProperty superPropertyMatch_;

	IndexedSubObjectPropertyOfAxiomMatch2(
			IndexedSubObjectPropertyOfAxiomMatch1 parent,
			ElkSubObjectPropertyExpression subPropertyChainMatch,
			ElkObjectProperty superPropertyMatch) {
		super(parent);
		this.subPropertyChainMatch_ = subPropertyChainMatch;
		this.superPropertyMatch_ = superPropertyMatch;
	}

	public ElkSubObjectPropertyExpression getSubPropertyChainMatch() {
		return subPropertyChainMatch_;
	}

	public ElkObjectProperty getSuperPropertyMatch() {
		return superPropertyMatch_;
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

		IndexedSubObjectPropertyOfAxiomMatch2 getIndexedSubObjectPropertyOfAxiomMatch2(
				IndexedSubObjectPropertyOfAxiomMatch1 parent,
				ElkSubObjectPropertyExpression subPropertyChainMatch,
				ElkObjectProperty superPropertyMatch);

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

		O visit(IndexedSubObjectPropertyOfAxiomMatch2 conclusionMatch);

	}

}
