
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents a transformation of an {@link ElkSubClassOfAxiom} to an
 * {@link IndexedSubClassOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkSubClassOfAxiomConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkSubClassOfAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkSubClassOfAxiomConversion inference);

	}

}
