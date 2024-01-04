
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiom;

public class IndexedSubObjectPropertyOfAxiomMatch1
		extends AbstractIndexedAxiomMatch<IndexedSubObjectPropertyOfAxiom> {

	IndexedSubObjectPropertyOfAxiomMatch1(
			IndexedSubObjectPropertyOfAxiom parent) {
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

		IndexedSubObjectPropertyOfAxiomMatch1 getIndexedSubObjectPropertyOfAxiomMatch1(
				IndexedSubObjectPropertyOfAxiom parent);

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

		O visit(IndexedSubObjectPropertyOfAxiomMatch1 conclusionMatch);

	}

}
