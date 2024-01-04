
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkIrreflexiveObjectPropertyAxiomVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Irreflexive_Object_Properties">
 * Irreflexive Object Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkIrreflexiveObjectPropertyAxiom extends
		ElkObjectPropertyAxiom, ElkPropertyAxiom<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkIrreflexiveObjectPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkIrreflexiveObjectPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkIrreflexiveObjectPropertyAxiom}.
		 * 
		 * @param property
		 *            the irreflexive {@link ElkObjectPropertyExpression} for
		 *            which the axiom should be created
		 * @return an {@link ElkIrreflexiveObjectPropertyAxiom} corresponding to
		 *         the input
		 */
		public ElkIrreflexiveObjectPropertyAxiom getIrreflexiveObjectPropertyAxiom(
				ElkObjectPropertyExpression property);

	}

}
