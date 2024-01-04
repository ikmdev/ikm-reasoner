
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityUnqualifiedVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Minimum_Cardinality_2">minimum
 * cardinality restriction<a> in the OWL 2 specification in the case the
 * qualified data range is empty.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataMinCardinalityUnqualified
		extends ElkDataMinCardinality {

	/**
	 * Accept an {@link ElkDataMinCardinalityUnqualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataMinCardinalityUnqualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataMinCardinalityUnqualified}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @return an {@link ElkDataMinCardinalityUnqualified} corresponding to
		 *         the input
		 */
		public ElkDataMinCardinalityUnqualified getDataMinCardinalityUnqualified(
				ElkDataPropertyExpression property,
				int cardinality);

	}

}
