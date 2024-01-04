
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Represents a transformation of an {@link ElkAxiom} to an
 * {@link IndexedDeclarationAxiom}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface IndexedDeclarationAxiomInference
		extends IndexedAxiomInference {

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public IndexedDeclarationAxiom getConclusion(
			IndexedDeclarationAxiom.Factory factory);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends ElkDeclarationAxiomConversion.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
