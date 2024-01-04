
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityUnqualifiedVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Maximum_Cardinality_2">maximum cardinality
 * restriction<a> in the OWL 2 specification in the case the qualified data
 * range is empty.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataMaxCardinalityUnqualified extends ElkDataMaxCardinality {

	/**
	 * Accept an {@link ElkDataMaxCardinalityUnqualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataMaxCardinalityUnqualifiedVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataMaxCardinalityUnqualified}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param cardinality
		 *            the cardinality for which the object should be created
		 * @return an {@link ElkDataMaxCardinalityUnqualified} corresponding to the
		 *         input
		 */
		public ElkDataMaxCardinalityUnqualified getDataMaxCardinalityUnqualified(
				ElkDataPropertyExpression property, int cardinality);
	}

}
