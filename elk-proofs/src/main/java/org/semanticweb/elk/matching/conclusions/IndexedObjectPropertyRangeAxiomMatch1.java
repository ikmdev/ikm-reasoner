
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiom;

public class IndexedObjectPropertyRangeAxiomMatch1
		extends AbstractIndexedAxiomMatch<IndexedObjectPropertyRangeAxiom> {

	IndexedObjectPropertyRangeAxiomMatch1(
			IndexedObjectPropertyRangeAxiom parent) {
		super(parent);
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

		IndexedObjectPropertyRangeAxiomMatch1 getIndexedObjectPropertyRangeAxiomMatch1(
				IndexedObjectPropertyRangeAxiom parent);

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

		O visit(IndexedObjectPropertyRangeAxiomMatch1 conclusionMatch);

	}

}
