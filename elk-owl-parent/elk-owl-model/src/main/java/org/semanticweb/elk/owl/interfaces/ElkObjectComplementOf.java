
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectComplementOfVisitor;

/**
 * Corresponds to the
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Complement_of_Class_Expressions"
 * >complement of a class expression<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectComplementOf extends ElkClassExpression {

	/**
	 * Get the class expression that this expression negation of.
	 * 
	 * @return class expression
	 */
	public ElkClassExpression getClassExpression();

	/**
	 * Accept an {@link ElkObjectComplementOfVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectComplementOfVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectComplementOf}.
		 * 
		 * @param negated
		 * @return an {@link ElkObjectComplementOf} corresponding to the input
		 */
		public ElkObjectComplementOf getObjectComplementOf(
				ElkClassExpression negated);

	}
}
