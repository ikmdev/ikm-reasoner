
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityUnqualifiedVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Exact_Cardinality">exact
 * cardinality restriction<a> in the OWL 2 specification in the case the
 * qualified class expression is not specified.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectExactCardinalityUnqualified
		extends ElkObjectExactCardinality {

	/**
	 * Accept an {@link ElkObjectExactCardinalityUnqualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectExactCardinalityUnqualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectExactCardinalityUnqualified}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            object should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @return an {@link ElkObjectExactCardinalityUnqualified} corresponding
		 *         to the input
		 */
		public ElkObjectExactCardinalityUnqualified getObjectExactCardinalityUnqualified(
				ElkObjectPropertyExpression property,
				int cardinality);
	}

}
