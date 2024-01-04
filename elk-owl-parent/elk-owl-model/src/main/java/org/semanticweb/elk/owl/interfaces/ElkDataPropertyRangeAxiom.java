
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataPropertyRangeAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Data_Property_Range">Data Property
 * Range<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkDataPropertyRangeAxiom extends ElkDataPropertyAxiom,
		ElkPropertyRangeAxiom<ElkDataPropertyExpression, ElkDataRange> {

	/**
	 * Accept an {@link ElkDataPropertyRangeAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkDataPropertyRangeAxiomVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataPropertyRangeAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param range
		 *            the {@link ElkDataRange} for which the object should be
		 *            created
		 * @return an {@link ElkDataPropertyRangeAxiom} corresponding to the input
		 */
		public ElkDataPropertyRangeAxiom getDataPropertyRangeAxiom(
				ElkDataPropertyExpression property,
				ElkDataRange range);

	}
}
