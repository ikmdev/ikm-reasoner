
/**
 * @author Markus Kroetzsch, Aug 10, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkNegativeDataPropertyAssertionAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Negative_Data_Property_Assertions" >
 * negative data property assertion axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkNegativeDataPropertyAssertionAxiom extends
		ElkPropertyAssertionAxiom<ElkDataPropertyExpression, ElkIndividual, ElkLiteral> {

	/**
	 * Accept an {@link ElkNegativeDataPropertyAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkNegativeDataPropertyAssertionAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkNegativeDataPropertyAssertionAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the axiom
		 *            should be created
		 * @param subject
		 *            the {@link ElkIndividual} for which the axiom should be
		 *            created
		 * @param object
		 *            the {@link ElkLiteral} for which the axiom should be
		 *            created
		 * @return an {@link ElkNegativeDataPropertyAssertionAxiom}
		 *         corresponding to the input
		 */
		public ElkNegativeDataPropertyAssertionAxiom getNegativeDataPropertyAssertionAxiom(
				ElkDataPropertyExpression property,
				ElkIndividual subject, ElkLiteral object);

	}
}
