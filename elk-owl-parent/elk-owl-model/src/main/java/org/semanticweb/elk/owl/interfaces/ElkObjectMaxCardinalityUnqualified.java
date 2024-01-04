
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityUnqualifiedVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Maximum_Cardinality">maximum
 * cardinality restriction<a> in the OWL 2 specification in the case the
 * qualified class expression is not specified.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectMaxCardinalityUnqualified
		extends ElkObjectMaxCardinality {

	/**
	 * Accept an {@link ElkObjectMaxCardinalityUnqualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectMaxCardinalityUnqualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectMaxCardinalityUnqualified}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            object should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @return an {@link ElkObjectMaxCardinalityUnqualified} corresponding
		 *         to the input
		 */
		public ElkObjectMaxCardinalityUnqualified getObjectMaxCardinalityUnqualified(
				ElkObjectPropertyExpression property,
				int cardinality);

	}

}
