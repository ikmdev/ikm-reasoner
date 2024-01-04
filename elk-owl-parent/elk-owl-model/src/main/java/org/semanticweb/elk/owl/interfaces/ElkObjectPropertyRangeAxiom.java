
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectPropertyRangeAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Property_Range">Object
 * Property Range<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkObjectPropertyRangeAxiom extends ElkObjectPropertyAxiom,
		ElkPropertyRangeAxiom<ElkObjectPropertyExpression, ElkClassExpression> {

	/**
	 * Accept an {@link ElkObjectPropertyRangeAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkObjectPropertyRangeAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectPropertyRangeAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            axiom should be created
		 * @param range
		 *            the {@link ElkClassExpression} for which the axiom should
		 *            be created
		 * @return an {@link ElkObjectPropertyRangeAxiom} corresponding to the
		 *         input
		 */
		public ElkObjectPropertyRangeAxiom getObjectPropertyRangeAxiom(
				ElkObjectPropertyExpression property,
				ElkClassExpression range);

	}
}
