
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkFunctionalDataPropertyAxiomVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Functional_Data_Properties">
 * Functional Data Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkFunctionalDataPropertyAxiom extends ElkDataPropertyAxiom,
		ElkPropertyAxiom<ElkDataPropertyExpression> {

	/**
	 * Accept an {@link ElkFunctionalDataPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkFunctionalDataPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkFunctionalDataPropertyAxiom}.
		 * 
		 * @param property
		 *            the functional {@link ElkDataPropertyExpression} for which
		 *            the axiom should be created
		 * @return an {@link ElkFunctionalDataPropertyAxiom} corresponding to
		 *         the input
		 */
		public ElkFunctionalDataPropertyAxiom getFunctionalDataPropertyAxiom(
				ElkDataPropertyExpression property);

	}
}
