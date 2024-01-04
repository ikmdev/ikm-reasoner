
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiom;

public class IndexedSubClassOfAxiomMatch1
		extends AbstractIndexedAxiomMatch<IndexedSubClassOfAxiom> {

	IndexedSubClassOfAxiomMatch1(IndexedSubClassOfAxiom parent) {
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

		IndexedSubClassOfAxiomMatch1 getIndexedSubClassOfAxiomMatch1(
				IndexedSubClassOfAxiom parent);

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

		O visit(IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
