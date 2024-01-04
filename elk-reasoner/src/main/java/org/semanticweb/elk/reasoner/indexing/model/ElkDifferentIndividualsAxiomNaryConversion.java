
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;

/**
 * Represents a transformation of an {@link ElkDifferentIndividualsAxiom} to an
 * {@link IndexedDisjointClassesAxiom}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ElkDifferentIndividualsAxiomNaryConversion
		extends
			IndexedDisjointClassesAxiomInference {

	@Override
	ElkDifferentIndividualsAxiom getOriginalAxiom();	

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDifferentIndividualsAxiomNaryConversion inference);

	}

}
