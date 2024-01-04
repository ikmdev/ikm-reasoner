
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * Represents a transformation of an {@link ElkDisjointUnionAxiom} to an
 * {@link IndexedDisjointClassesAxiom} representing the disjointness of members
 * of the union.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkDisjointUnionAxiomNaryConversion
		extends
			IndexedDisjointClassesAxiomInference {

	@Override
	ElkDisjointUnionAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDisjointUnionAxiomNaryConversion inference);

	}

}
