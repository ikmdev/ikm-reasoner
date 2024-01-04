 
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkSubClassOfAxiomVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Subclass_Axioms">Subclass Axiom
 * <a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkSubClassOfAxiom extends ElkClassAxiom {

	/**
	 * Get the sub class expression of this axiom.
	 * 
	 * @return sub class expression
	 */
	public ElkClassExpression getSubClassExpression();

	/**
	 * Get the super class expression of this axiom.
	 * 
	 * @return super class expression
	 */
	public ElkClassExpression getSuperClassExpression();

	/**
	 * Accept an {@link ElkSubClassOfAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkSubClassOfAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkSubClassOfAxiom}.
		 * 
		 * @param subClassExpression
		 *            the {@link ElkClassExpression} for which the axiom should
		 *            be created
		 * @param superClassExpression
		 *            the {@link ElkClassExpression} for which the axiom should
		 *            be created
		 * @return an {@link ElkSubClassOfAxiom} corresponding to the input
		 */
		public ElkSubClassOfAxiom getSubClassOfAxiom(
				ElkClassExpression subClassExpression,
				ElkClassExpression superClassExpression);

	}

}
