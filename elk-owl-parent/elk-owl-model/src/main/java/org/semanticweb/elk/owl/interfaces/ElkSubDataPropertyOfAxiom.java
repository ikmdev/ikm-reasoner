
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkSubDataPropertyOfAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Data_Subproperties">Data
 * Subproperty Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkSubDataPropertyOfAxiom extends ElkDataPropertyAxiom {

	/**
	 * Get the sub data property expression of this axiom.
	 * 
	 * @return sub data property expression
	 */
	public ElkDataPropertyExpression getSubDataPropertyExpression();

	/**
	 * Get the super data property expression of this axiom.
	 * 
	 * @return super data property expression
	 */
	public ElkDataPropertyExpression getSuperDataPropertyExpression();

	/**
	 * Accept an {@link ElkSubDataPropertyOfAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkSubDataPropertyOfAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkSubDataPropertyOfAxiom}.
		 * 
		 * @param subProperty
		 *            the sub-{@link ElkDataPropertyExpression} for which the
		 *            axiom should be created
		 * @param superProperty
		 *            the super-{@link ElkDataPropertyExpression} for which the
		 *            axiom should be created
		 * @return an {@link ElkSubDataPropertyOfAxiom} corresponding to the
		 *         input
		 */
		public ElkSubDataPropertyOfAxiom getSubDataPropertyOfAxiom(
				ElkDataPropertyExpression subProperty,
				ElkDataPropertyExpression superProperty);
	}

}
