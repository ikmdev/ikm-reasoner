
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedDeclarationAxiomInference} that can be modified as a result
 * of updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedDeclarationAxiomInference extends
		ModifiableIndexedAxiomInference, IndexedDeclarationAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public ModifiableIndexedDeclarationAxiom getConclusion(
			ModifiableIndexedDeclarationAxiom.Factory factory);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ModifiableElkDeclarationAxiomConversion.Factory {

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
			extends ModifiableElkDeclarationAxiomConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
