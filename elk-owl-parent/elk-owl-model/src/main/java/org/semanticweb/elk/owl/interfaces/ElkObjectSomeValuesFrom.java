
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectSomeValuesFromVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Existential_Quantification">
 * Existential Quantification Object Property Restriction<a> in the OWL 2
 * specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectSomeValuesFrom extends
		ElkPropertyRestrictionQualified<ElkObjectPropertyExpression, ElkClassExpression> {

	/**
	 * Accept an {@link ElkObjectSomeValuesFromVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectSomeValuesFromVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectSomeValuesFrom}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            object should be created
		 * @param filler
		 *            the {@link ElkClassExpression} for which the object should
		 *            be created
		 * @return an {@link ElkObjectSomeValuesFrom} corresponding to the input
		 */
		public ElkObjectSomeValuesFrom getObjectSomeValuesFrom(
				ElkObjectPropertyExpression property,
				ElkClassExpression filler);
	}

}
