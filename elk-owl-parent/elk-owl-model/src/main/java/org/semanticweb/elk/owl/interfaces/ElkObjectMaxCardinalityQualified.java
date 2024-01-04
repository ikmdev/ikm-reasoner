
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityQualifiedVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Maximum_Cardinality">maximum
 * cardinality restriction<a> in the OWL 2 specification in the case the
 * qualified class expression is specified.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectMaxCardinalityQualified
		extends ElkObjectMaxCardinality,
		ElkCardinalityRestrictionQualified<ElkObjectPropertyExpression, ElkClassExpression> {

	/**
	 * Accept an {@link ElkObjectMaxCardinalityQualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectMaxCardinalityQualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectMaxCardinalityQualified}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            object should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @param filler
		 *            the {@link ElkClassExpression} for which the object should
		 *            be created
		 * @return an {@link ElkObjectMaxCardinalityQualified} corresponding to
		 *         the input
		 */
		public ElkObjectMaxCardinalityQualified getObjectMaxCardinalityQualified(
				ElkObjectPropertyExpression property,
				int cardinality, ElkClassExpression filler);
	}
}
