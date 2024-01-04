
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedDisjointClassesAxiomInference} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedDisjointClassesAxiomInference extends
		ModifiableIndexedAxiomInference, IndexedDisjointClassesAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	ModifiableIndexedDisjointClassesAxiom getConclusion(
			ModifiableIndexedDisjointClassesAxiom.Factory factory);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends
			ModifiableElkDifferentIndividualsAxiomNaryConversion.Factory,
			ModifiableElkDisjointClassesAxiomNaryConversion.Factory,
			ModifiableElkDisjointUnionAxiomNaryConversion.Factory {

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
	interface Visitor<O> extends
			ModifiableElkDifferentIndividualsAxiomNaryConversion.Visitor<O>,
			ModifiableElkDisjointClassesAxiomNaryConversion.Visitor<O>,
			ModifiableElkDisjointUnionAxiomNaryConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
