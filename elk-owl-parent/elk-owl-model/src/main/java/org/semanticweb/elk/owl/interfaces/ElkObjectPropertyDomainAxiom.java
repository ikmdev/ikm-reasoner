
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectPropertyDomainAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Property_Domain">Object
 * Property Domain<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkObjectPropertyDomainAxiom extends ElkObjectPropertyAxiom,
		ElkPropertyDomainAxiom<ElkObjectPropertyExpression, ElkClassExpression> {

	/**
	 * Accept an {@link ElkObjectPropertyDomainAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkObjectPropertyDomainAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectPropertyDomainAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkObjectPropertyExpression} for which the
		 *            axiom should be created
		 * @param domain
		 *            the {@link ElkClassExpression} for which the axiom should
		 *            be created
		 * @return an {@link ElkObjectPropertyDomainAxiom} corresponding to the
		 *         input
		 */
		public ElkObjectPropertyDomainAxiom getObjectPropertyDomainAxiom(
				ElkObjectPropertyExpression property,
				ElkClassExpression domain);

	}

}
