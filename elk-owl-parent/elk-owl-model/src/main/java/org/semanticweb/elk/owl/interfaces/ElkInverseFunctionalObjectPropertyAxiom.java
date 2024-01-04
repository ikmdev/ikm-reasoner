
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkInverseFunctionalObjectPropertyAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Inverse-Functional_Object_Properties" >
 * Inverse Functional Object Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkInverseFunctionalObjectPropertyAxiom extends
		ElkObjectPropertyAxiom, ElkPropertyAxiom<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkInverseFunctionalObjectPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkInverseFunctionalObjectPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkInverseFunctionalObjectPropertyAxiom}.
		 * 
		 * @param property
		 *            the inverse functional {@link ElkObjectPropertyExpression}
		 *            for which the axiom should be created
		 * @return an {@link ElkInverseFunctionalObjectPropertyAxiom}
		 *         corresponding to the input
		 */
		public ElkInverseFunctionalObjectPropertyAxiom getInverseFunctionalObjectPropertyAxiom(
				ElkObjectPropertyExpression property);

	}
}
