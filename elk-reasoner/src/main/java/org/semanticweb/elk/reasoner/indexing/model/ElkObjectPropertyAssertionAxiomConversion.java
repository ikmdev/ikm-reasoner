
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;

/**
 * Represents a transformation of an {@link ElkObjectPropertyAssertionAxiom} to
 * an {@link IndexedSubClassOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkObjectPropertyAssertionAxiomConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkObjectPropertyAssertionAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkObjectPropertyAssertionAxiomConversion inference);

	}

}
