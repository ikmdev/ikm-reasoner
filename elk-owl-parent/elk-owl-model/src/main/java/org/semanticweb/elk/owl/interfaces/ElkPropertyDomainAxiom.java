
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkPropertyDomainAxiomVisitor;

/**
 * A generic interface for for object and data property domain axioms.
 * properties.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this axiom
 * @param <D>
 *            the type of the domain of this axiom
 */
public interface ElkPropertyDomainAxiom<P, D> extends ElkPropertyAxiom<P> {

	/**
	 * Get the domain of this axiom.
	 * 
	 * @return the domain of this axiom
	 */
	D getDomain();

	/**
	 * Accept an {@link ElkPropertyDomainAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkPropertyDomainAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkAnnotationPropertyDomainAxiom.Factory,
			ElkDataPropertyDomainAxiom.Factory,
			ElkObjectPropertyDomainAxiom.Factory {

		// combined interface

	}

}
