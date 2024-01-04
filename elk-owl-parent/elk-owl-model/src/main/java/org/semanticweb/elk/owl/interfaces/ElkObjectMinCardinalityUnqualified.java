
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityUnqualifiedVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Minimum_Cardinality">minimum
 * cardinality restriction<a> in the OWL 2 specification in the case the
 * qualified class expression is not specified.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectMinCardinalityUnqualified
		extends ElkObjectMinCardinality {

	/**
	 * Accept an {@link ElkObjectMinCardinalityUnqualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectMinCardinalityUnqualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectMinCardinalityUnqualified}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            object should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @return an {@link ElkObjectMinCardinalityUnqualified} corresponding
		 *         to the input
		 */
		public ElkObjectMinCardinalityUnqualified getObjectMinCardinalityUnqualified(
				ElkObjectPropertyExpression property,
				int cardinality);
	}

}
