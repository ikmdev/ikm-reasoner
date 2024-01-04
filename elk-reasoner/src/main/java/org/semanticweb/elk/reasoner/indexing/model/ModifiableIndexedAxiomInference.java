
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedAxiomInference} that can be modified as a result of updating
 * the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedAxiomInference extends IndexedAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ModifiableIndexedDisjointClassesAxiomInference.Factory,
			ModifiableIndexedSubClassOfAxiomInference.Factory,
			ModifiableIndexedEquivalentClassesAxiomInference.Factory,
			ModifiableIndexedSubObjectPropertyOfAxiomInference.Factory,
			ModifiableIndexedObjectPropertyRangeAxiomInference.Factory,
			ModifiableIndexedDeclarationAxiomInference.Factory {

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
			extends ModifiableIndexedDisjointClassesAxiomInference.Visitor<O>,
			ModifiableIndexedSubClassOfAxiomInference.Visitor<O>,
			ModifiableIndexedEquivalentClassesAxiomInference.Visitor<O>,
			ModifiableIndexedSubObjectPropertyOfAxiomInference.Visitor<O>,
			ModifiableIndexedObjectPropertyRangeAxiomInference.Visitor<O>,
			ModifiableIndexedDeclarationAxiomInference.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
