
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Represents a transformation of an {@link ElkAxiom} to an
 * {@link IndexedEquivalentClassesAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface IndexedEquivalentClassesAxiomInference extends IndexedAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public IndexedEquivalentClassesAxiom getConclusion(IndexedEquivalentClassesAxiom.Factory factory);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends ElkDisjointUnionAxiomEquivalenceConversion.Visitor<O>,
			ElkEquivalentClassesAxiomEquivalenceConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
