
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityUnqualifiedVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Exact_Cardinality_2">exact
 * cardinality restriction<a> in the OWL 2 specification in the case the
 * qualified data range is empty.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataExactCardinalityUnqualified
		extends ElkDataExactCardinality {

	/**
	 * Accept an {@link ElkDataExactCardinalityUnqualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataExactCardinalityUnqualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataExactCardinalityUnqualified}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @return an {@link ElkDataExactCardinalityUnqualified} corresponding
		 *         to the input
		 */
		public ElkDataExactCardinalityUnqualified getDataExactCardinalityUnqualified(
				ElkDataPropertyExpression property,
				int cardinality);

	}

}
