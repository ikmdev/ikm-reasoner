
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectHasSelfVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Self-Restriction">Self-
 * Restriction<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectHasSelf
		extends ElkPropertyRestriction<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkObjectHasSelfVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectHasSelfVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectHasSelf}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            object should be created
		 * @return an {@link ElkObjectHasSelf} corresponding to the input
		 */
		public ElkObjectHasSelf getObjectHasSelf(
				ElkObjectPropertyExpression property);

	}
}
