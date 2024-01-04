
/**
 * @author Markus Kroetzsch, Aug 10, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataPropertyAssertionAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Positive_Data_Property_Assertions"
 * >positive data property assertion axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkDataPropertyAssertionAxiom
		extends
		ElkPropertyAssertionAxiom<ElkDataPropertyExpression, ElkIndividual, ElkLiteral> {

	/**
	 * Accept an {@link ElkDataPropertyAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataPropertyAssertionAxiomVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataPropertyAssertionAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param subject
		 *            the {@link ElkIndividual} for which the object should be
		 *            created
		 * @param object
		 *            the {@link ElkLiteral} for which the object should be created
		 * @return an {@link ElkDataPropertyAssertionAxiom} corresponding to the
		 *         input
		 */
		public ElkDataPropertyAssertionAxiom getDataPropertyAssertionAxiom(
				ElkDataPropertyExpression property,
				ElkIndividual subject, ElkLiteral object);

	}

}
