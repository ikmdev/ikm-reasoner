
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataHasValueVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Literal_Value_Restriction">Literal Value
 * Restriction for Data Properties<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataHasValue extends
		ElkPropertyRestrictionQualified<ElkDataPropertyExpression, ElkLiteral> {

	/**
	 * Accept an {@link ElkDataHasValueVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataHasValueVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataHasValue}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param value
		 *            the {@link ElkLiteral} for which the object should be created
		 * @return an {@link ElkDataHasValue} corresponding to the input
		 */
		public ElkDataHasValue getDataHasValue(
				ElkDataPropertyExpression property, ElkLiteral value);

	}

}
