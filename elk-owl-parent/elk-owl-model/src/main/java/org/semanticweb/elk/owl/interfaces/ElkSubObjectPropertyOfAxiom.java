
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyOfAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Object_Subproperties">Object Subproperty
 * Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkSubObjectPropertyOfAxiom extends ElkObjectPropertyAxiom {

	/**
	 * Get the sub object property expression of this axiom.
	 * 
	 * @return sub object property expression
	 */
	public ElkSubObjectPropertyExpression getSubObjectPropertyExpression();

	/**
	 * Get the super object property expression of this axiom.
	 * 
	 * @return super object property expression
	 */
	public ElkObjectPropertyExpression getSuperObjectPropertyExpression();

	/**
	 * Accept an {@link ElkSubObjectPropertyOfAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkSubObjectPropertyOfAxiomVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkSubObjectPropertyOfAxiom}.
		 * 
		 * @param subProperty
		 *            the {@link ElkSubObjectPropertyExpression} for which the axiom
		 *            should be created
		 * @param superProperty
		 *            the super-{@link ElkObjectPropertyExpression} for which the
		 *            axiom should be created
		 * @return an {@link ElkSubObjectPropertyOfAxiom} corresponding to the input
		 */
		public ElkSubObjectPropertyOfAxiom getSubObjectPropertyOfAxiom(
				ElkSubObjectPropertyExpression subProperty,
				ElkObjectPropertyExpression superProperty);

	}

}
