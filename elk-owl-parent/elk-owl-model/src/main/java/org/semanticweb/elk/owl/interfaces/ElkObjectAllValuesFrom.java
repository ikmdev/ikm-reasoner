
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectAllValuesFromVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Universal_Quantification">
 * Universal Quantification Object Property Restriction<a> in the OWL 2
 * specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectAllValuesFrom extends
		ElkPropertyRestrictionQualified<ElkObjectPropertyExpression, ElkClassExpression> {

	/**
	 * Accept an {@link ElkObjectAllValuesFromVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectAllValuesFromVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectAllValuesFrom}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            axiom should be created
		 * @param filler
		 *            the {@link ElkClassExpression} for which the axiom should
		 *            be created
		 * @return an {@link ElkObjectAllValuesFrom} corresponding to the input
		 */
		public ElkObjectAllValuesFrom getObjectAllValuesFrom(
				ElkObjectPropertyExpression property,
				ElkClassExpression filler);

	}

}
