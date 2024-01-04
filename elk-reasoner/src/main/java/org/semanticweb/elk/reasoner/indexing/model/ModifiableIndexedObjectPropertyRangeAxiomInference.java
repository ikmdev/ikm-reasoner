
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedObjectPropertyRangeAxiomInference} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedObjectPropertyRangeAxiomInference
		extends ModifiableIndexedAxiomInference,
		IndexedObjectPropertyRangeAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public ModifiableIndexedObjectPropertyRangeAxiom getConclusion(
			ModifiableIndexedObjectPropertyRangeAxiom.Factory factory);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ModifiableElkObjectPropertyRangeAxiomConversion.Factory {

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
			extends ModifiableElkObjectPropertyRangeAxiomConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
