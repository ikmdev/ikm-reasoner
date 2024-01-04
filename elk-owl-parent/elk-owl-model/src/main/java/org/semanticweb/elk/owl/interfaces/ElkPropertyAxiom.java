
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;

/**
 * A generic interface for axioms with data properties or object properties.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this axiom
 */
public interface ElkPropertyAxiom<P> extends ElkAxiom {

	/**
	 * Get the property of this axiom.
	 * 
	 * @return the property of this axiom
	 */
	P getProperty();

	/**
	 * Accept an {@link ElkPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkAsymmetricObjectPropertyAxiom.Factory,
			ElkFunctionalDataPropertyAxiom.Factory,
			ElkFunctionalObjectPropertyAxiom.Factory,
			ElkInverseFunctionalObjectPropertyAxiom.Factory,
			ElkIrreflexiveObjectPropertyAxiom.Factory,
			ElkPropertyAssertionAxiom.Factory, ElkPropertyDomainAxiom.Factory,
			ElkPropertyRangeAxiom.Factory,
			ElkReflexiveObjectPropertyAxiom.Factory,
			ElkSymmetricObjectPropertyAxiom.Factory,
			ElkTransitiveObjectPropertyAxiom.Factory {

		// combined interface

	}

}
