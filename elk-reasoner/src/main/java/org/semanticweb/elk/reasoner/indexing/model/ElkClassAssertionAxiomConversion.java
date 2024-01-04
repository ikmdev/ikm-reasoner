
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;

/**
 * Represents a transformation of an {@link ElkClassAssertionAxiom} to an
 * {@link IndexedSubClassOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ElkClassAssertionAxiomConversion
		extends IndexedSubClassOfAxiomInference {

	@Override
	ElkClassAssertionAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkClassAssertionAxiomConversion inference);

	}

}
