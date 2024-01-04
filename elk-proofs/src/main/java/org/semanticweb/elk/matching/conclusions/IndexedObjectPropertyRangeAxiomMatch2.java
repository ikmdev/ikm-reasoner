
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

public class IndexedObjectPropertyRangeAxiomMatch2
		extends
			AbstractIndexedAxiomMatch<IndexedObjectPropertyRangeAxiomMatch1> {

	private final ElkObjectProperty propertyMatch_;

	private final ElkClassExpression rangeMatch_;

	IndexedObjectPropertyRangeAxiomMatch2(
			IndexedObjectPropertyRangeAxiomMatch1 parent,
			ElkObjectProperty propertyMatch, ElkClassExpression rangeMatch) {
		super(parent);
		this.propertyMatch_ = propertyMatch;
		this.rangeMatch_ = rangeMatch;
	}

	public ElkObjectProperty getPropertyMatch() {
		return propertyMatch_;
	}

	public ElkClassExpression getRangeMatch() {
		return rangeMatch_;
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

		IndexedObjectPropertyRangeAxiomMatch2 getIndexedObjectPropertyRangeAxiomMatch2(
				IndexedObjectPropertyRangeAxiomMatch1 parent,
				ElkObjectProperty propertyMatch, ElkClassExpression rangeMatch);

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

		O visit(IndexedObjectPropertyRangeAxiomMatch2 conclusionMatch);

	}

}
