
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedSubObjectPropertyOfAxiomInference} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedSubObjectPropertyOfAxiomInference
		extends ModifiableIndexedAxiomInference,
		IndexedSubObjectPropertyOfAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public ModifiableIndexedSubObjectPropertyOfAxiom getConclusion(
			ModifiableIndexedSubObjectPropertyOfAxiom.Factory factory);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends
			ModifiableElkEquivalentObjectPropertiesAxiomConversion.Factory,
			ModifiableElkSubObjectPropertyOfAxiomConversion.Factory,
			ModifiableElkTransitiveObjectPropertyAxiomConversion.Factory {

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
			ModifiableElkEquivalentObjectPropertiesAxiomConversion.Visitor<O>,
			ModifiableElkSubObjectPropertyOfAxiomConversion.Visitor<O>,
			ModifiableElkTransitiveObjectPropertyAxiomConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
