
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * Represents a transformation of an {@link ElkDisjointClassesAxiom} to an
 * {@link IndexedDisjointClassesAxiom}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ElkDisjointClassesAxiomNaryConversion
		extends
			IndexedDisjointClassesAxiomInference {

	@Override
	ElkDisjointClassesAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDisjointClassesAxiomNaryConversion inference);

	}

}
