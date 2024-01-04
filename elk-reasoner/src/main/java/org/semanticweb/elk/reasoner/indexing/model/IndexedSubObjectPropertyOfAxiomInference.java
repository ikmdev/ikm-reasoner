
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Represents a transformation of an {@link ElkAxiom} to an
 * {@link IndexedAxiomInference}.
 * 
 * @author Yevgeny Kazakov
 * 
 */
public interface IndexedSubObjectPropertyOfAxiomInference
		extends
			IndexedAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public IndexedSubObjectPropertyOfAxiom getConclusion(
			IndexedSubObjectPropertyOfAxiom.Factory factory);

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
				ElkEquivalentObjectPropertiesAxiomConversion.Visitor<O>,
				ElkSubObjectPropertyOfAxiomConversion.Visitor<O>,
				ElkTransitiveObjectPropertyAxiomConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
