
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiom;

public class IndexedDisjointClassesAxiomMatch1
		extends AbstractIndexedAxiomMatch<IndexedDisjointClassesAxiom> {

	IndexedDisjointClassesAxiomMatch1(IndexedDisjointClassesAxiom parent) {
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

		IndexedDisjointClassesAxiomMatch1 getIndexedDisjointClassesAxiomMatch1(
				IndexedDisjointClassesAxiom parent);

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

		O visit(IndexedDisjointClassesAxiomMatch1 conclusionMatch);

	}

}
