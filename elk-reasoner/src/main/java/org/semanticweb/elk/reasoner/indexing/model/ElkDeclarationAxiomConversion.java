
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;

/**
 * Represents a transformation of an {@link ElkDeclarationAxiom} to an
 * {@link IndexedDeclarationAxiom}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ElkDeclarationAxiomConversion
		extends
			IndexedDeclarationAxiomInference {

	@Override
	ElkDeclarationAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDeclarationAxiomConversion inference);

	}

}
