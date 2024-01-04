
/**
 * @author Markus Kroetzsch, Aug 10, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkNegativeObjectPropertyAssertionAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Negative_Object_Property_Assertions" >
 * negative object property assertion axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkNegativeObjectPropertyAssertionAxiom extends
		ElkPropertyAssertionAxiom<ElkObjectPropertyExpression, ElkIndividual, ElkIndividual> {

	/**
	 * Accept an {@link ElkNegativeObjectPropertyAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkNegativeObjectPropertyAssertionAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkNegativeObjectPropertyAssertionAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            axiom should be created
		 * @param subject
		 *            the {@link ElkIndividual} for which the axiom should be
		 *            created
		 * @param object
		 *            the {@link ElkIndividual} for which the axiom should be
		 *            created
		 * @return an {@link ElkNegativeObjectPropertyAssertionAxiom}
		 *         corresponding to the input
		 */
		public ElkNegativeObjectPropertyAssertionAxiom getNegativeObjectPropertyAssertionAxiom(
				ElkObjectPropertyExpression property,
				ElkIndividual subject, ElkIndividual object);

	}
}
