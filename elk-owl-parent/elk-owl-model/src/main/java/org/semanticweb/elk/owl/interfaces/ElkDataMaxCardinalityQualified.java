
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityQualifiedVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Maximum_Cardinality_2">maximum cardinality
 * restriction<a> in the OWL 2 specification in the case the qualified data
 * range is not empty.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataMaxCardinalityQualified
		extends
		ElkDataMaxCardinality,
		ElkCardinalityRestrictionQualified<ElkDataPropertyExpression, ElkDataRange> {

	/**
	 * Accept an {@link ElkDataMaxCardinalityQualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataMaxCardinalityQualifiedVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataMaxCardinalityQualified}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @param range
		 *            the {@link ElkDataRange} for which the object should be
		 *            created
		 * @return an {@link ElkDataMaxCardinalityQualified} corresponding to the
		 *         input
		 */
		public ElkDataMaxCardinalityQualified getDataMaxCardinalityQualified(
				ElkDataPropertyExpression property, int cardinality,
				ElkDataRange range);

	}

}
