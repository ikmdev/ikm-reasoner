
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedSubClassOfAxiomInference} that can be modified as a result
 * of updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedSubClassOfAxiomInference extends
		ModifiableIndexedAxiomInference, IndexedSubClassOfAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	ModifiableIndexedSubClassOfAxiom getConclusion(
			ModifiableIndexedSubClassOfAxiom.Factory factory);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ModifiableElkClassAssertionAxiomConversion.Factory,
			ModifiableElkDifferentIndividualsAxiomBinaryConversion.Factory,
			ModifiableElkDisjointClassesAxiomBinaryConversion.Factory,
			ModifiableElkDisjointUnionAxiomOwlNothingConversion.Factory,
			ModifiableElkDisjointUnionAxiomSubClassConversion.Factory,
			ModifiableElkDisjointUnionAxiomBinaryConversion.Factory,
			ModifiableElkEquivalentClassesAxiomSubClassConversion.Factory,
			ModifiableElkObjectPropertyAssertionAxiomConversion.Factory,
			ModifiableElkObjectPropertyDomainAxiomConversion.Factory,
			ModifiableElkReflexiveObjectPropertyAxiomConversion.Factory,
			ModifiableElkSameIndividualAxiomConversion.Factory,
			ModifiableElkSubClassOfAxiomConversion.Factory {

		// combined interface

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends ModifiableElkClassAssertionAxiomConversion.Visitor<O>,
			ModifiableElkDifferentIndividualsAxiomBinaryConversion.Visitor<O>,
			ModifiableElkDisjointClassesAxiomBinaryConversion.Visitor<O>,
			ModifiableElkDisjointUnionAxiomOwlNothingConversion.Visitor<O>,
			ModifiableElkDisjointUnionAxiomSubClassConversion.Visitor<O>,
			ModifiableElkDisjointUnionAxiomBinaryConversion.Visitor<O>,
			ModifiableElkEquivalentClassesAxiomSubClassConversion.Visitor<O>,
			ModifiableElkObjectPropertyAssertionAxiomConversion.Visitor<O>,
			ModifiableElkObjectPropertyDomainAxiomConversion.Visitor<O>,
			ModifiableElkReflexiveObjectPropertyAxiomConversion.Visitor<O>,
			ModifiableElkSameIndividualAxiomConversion.Visitor<O>,
			ModifiableElkSubClassOfAxiomConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
