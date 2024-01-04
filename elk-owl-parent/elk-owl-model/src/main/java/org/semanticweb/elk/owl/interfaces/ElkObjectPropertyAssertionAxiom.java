
/**
 * @author Markus Kroetzsch, Aug 10, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAssertionAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Positive_Object_Property_Assertions" >
 * positive object property assertion axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkObjectPropertyAssertionAxiom extends
		ElkPropertyAssertionAxiom<ElkObjectPropertyExpression, ElkIndividual, ElkIndividual> {

	/**
	 * Accept an {@link ElkObjectPropertyAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkObjectPropertyAssertionAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectPropertyAssertionAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            axiom should be created
		 * @param subject
		 *            the first {@link ElkIndividual} for which the axiom should
		 *            be created
		 * @param object
		 *            the second {@link ElkIndividual} for which the axiom
		 *            should be created
		 * @return an {@link ElkObjectPropertyAssertionAxiom} corresponding to
		 *         the input
		 */
		public ElkObjectPropertyAssertionAxiom getObjectPropertyAssertionAxiom(
				ElkObjectPropertyExpression property,
				ElkIndividual subject, ElkIndividual object);
	}
}
