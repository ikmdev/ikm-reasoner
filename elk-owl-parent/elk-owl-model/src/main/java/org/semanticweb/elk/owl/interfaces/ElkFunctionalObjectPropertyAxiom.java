
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkFunctionalObjectPropertyAxiomVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Functional_Object_Properties">
 * Functional Object Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkFunctionalObjectPropertyAxiom extends
		ElkObjectPropertyAxiom, ElkPropertyAxiom<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkFunctionalObjectPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkFunctionalObjectPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkFunctionalObjectPropertyAxiom}.
		 * 
		 * @param property
		 *            the functional {@link ElkObjectPropertyExpression} for
		 *            which the axiom should be created
		 * @return an {@link ElkFunctionalObjectPropertyAxiom} corresponding to
		 *         the input
		 */
		public ElkFunctionalObjectPropertyAxiom getFunctionalObjectPropertyAxiom(
				ElkObjectPropertyExpression property);

	}

}
