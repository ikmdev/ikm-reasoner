
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkReflexiveObjectPropertyAxiomVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Reflexive_Object_Properties">
 * Reflexive Object Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkReflexiveObjectPropertyAxiom extends ElkObjectPropertyAxiom,
		ElkPropertyAxiom<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkReflexiveObjectPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkReflexiveObjectPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkReflexiveObjectPropertyAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            axiom should be created
		 * @return an {@link ElkReflexiveObjectPropertyAxiom} corresponding to
		 *         the input
		 */
		public ElkReflexiveObjectPropertyAxiom getReflexiveObjectPropertyAxiom(
				ElkObjectPropertyExpression property);

	}

}
