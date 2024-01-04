
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Represents a transformation of an {@link ElkAxiom} to an
 * {@link IndexedSubClassOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface IndexedSubClassOfAxiomInference extends IndexedAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	 IndexedSubClassOfAxiom getConclusion(
			IndexedSubClassOfAxiom.Factory factory);

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
				ElkClassAssertionAxiomConversion.Visitor<O>,
				ElkDifferentIndividualsAxiomBinaryConversion.Visitor<O>,
				ElkDisjointClassesAxiomBinaryConversion.Visitor<O>,
				ElkDisjointUnionAxiomOwlNothingConversion.Visitor<O>,
				ElkDisjointUnionAxiomSubClassConversion.Visitor<O>,
				ElkDisjointUnionAxiomBinaryConversion.Visitor<O>,
				ElkEquivalentClassesAxiomSubClassConversion.Visitor<O>,
				ElkObjectPropertyAssertionAxiomConversion.Visitor<O>,
				ElkObjectPropertyDomainAxiomConversion.Visitor<O>,
				ElkReflexiveObjectPropertyAxiomConversion.Visitor<O>,
				ElkSameIndividualAxiomConversion.Visitor<O>,
				ElkSubClassOfAxiomConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
