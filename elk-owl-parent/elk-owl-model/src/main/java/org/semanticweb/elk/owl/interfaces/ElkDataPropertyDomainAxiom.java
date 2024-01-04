
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataPropertyDomainAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Data_Property_Domain">Data Property
 * Domain<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkDataPropertyDomainAxiom extends ElkDataPropertyAxiom,
		ElkPropertyDomainAxiom<ElkDataPropertyExpression, ElkClassExpression> {

	/**
	 * Accept an {@link ElkDataPropertyDomainAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkDataPropertyDomainAxiomVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataPropertyDomainAxiom}.
		 * 
		 * @param property
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param domain
		 *            the {@link ElkClassExpression} for which the object should be
		 *            created
		 * @return an {@link ElkDataPropertyDomainAxiom} corresponding to the input
		 */
		public ElkDataPropertyDomainAxiom getDataPropertyDomainAxiom(
				ElkDataPropertyExpression property,
				ElkClassExpression domain);

	}
}
