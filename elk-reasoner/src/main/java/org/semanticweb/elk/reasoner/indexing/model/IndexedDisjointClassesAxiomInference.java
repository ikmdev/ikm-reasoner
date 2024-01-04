
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Represents a transformation of an {@link ElkAxiom} to an
 * {@link IndexedDisjointClassesAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface IndexedDisjointClassesAxiomInference
		extends
			IndexedAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	IndexedDisjointClassesAxiom getConclusion(IndexedDisjointClassesAxiom.Factory factory);
	
	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends
				ElkDifferentIndividualsAxiomNaryConversion.Visitor<O>,
				ElkDisjointClassesAxiomNaryConversion.Visitor<O>,
				ElkDisjointUnionAxiomNaryConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
