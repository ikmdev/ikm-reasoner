
/**
 * @author Markus Kroetzsch, Aug 10, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkClassAssertionAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Class_Assertions">class assertion
 * axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkClassAssertionAxiom extends ElkAssertionAxiom {

	/**
	 * Get the individual that this axiom refers to.
	 * 
	 * @return object property expression
	 */
	public ElkIndividual getIndividual();

	/**
	 * Get the class expression that this axiom refers to.
	 * 
	 * @return class expression
	 */
	public ElkClassExpression getClassExpression();

	/**
	 * Accept an {@link ElkClassAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkClassAssertionAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkClassAssertionAxiom}.
		 * 
		 * @param classExpression
		 *            the {@link ElkClassExpression} for which the axiom should
		 *            be created
		 * @param individual
		 *            the {@link ElkIndividual} for which the axiom should be
		 *            created
		 * @return an {@link ElkClassAssertionAxiom} corresponding to the input
		 */
		public ElkClassAssertionAxiom getClassAssertionAxiom(
				ElkClassExpression classExpression, ElkIndividual individual);

	}

}
