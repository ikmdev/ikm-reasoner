
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;

public class IndexedEquivalentClassesAxiomMatch1
		extends AbstractIndexedAxiomMatch<IndexedEquivalentClassesAxiom> {

	IndexedEquivalentClassesAxiomMatch1(IndexedEquivalentClassesAxiom parent) {
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

		IndexedEquivalentClassesAxiomMatch1 getIndexedEquivalentClassesAxiomMatch1(
				IndexedEquivalentClassesAxiom parent);

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

		O visit(IndexedEquivalentClassesAxiomMatch1 conclusionMatch);

	}

}
