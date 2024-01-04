
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedEquivalentClassesAxiomInference} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedEquivalentClassesAxiomInference
		extends ModifiableIndexedAxiomInference,
		IndexedEquivalentClassesAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public ModifiableIndexedEquivalentClassesAxiom getConclusion(
			ModifiableIndexedEquivalentClassesAxiom.Factory factory);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends
			ModifiableElkDisjointUnionAxiomEquivalenceConversion.Factory,
			ModifiableElkEquivalentClassesAxiomEquivalenceConversion.Factory {

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
			ModifiableElkDisjointUnionAxiomEquivalenceConversion.Visitor<O>,
			ModifiableElkEquivalentClassesAxiomEquivalenceConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
