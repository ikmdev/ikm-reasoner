
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Property_Axioms">Object
 * Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectPropertyAxiom extends ElkAxiom {

	/**
	 * Accept an {@link ElkObjectPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkAsymmetricObjectPropertyAxiom.Factory,
			ElkDisjointObjectPropertiesAxiom.Factory,
			ElkEquivalentObjectPropertiesAxiom.Factory,
			ElkFunctionalObjectPropertyAxiom.Factory,
			ElkInverseFunctionalObjectPropertyAxiom.Factory,
			ElkInverseObjectPropertiesAxiom.Factory,
			ElkIrreflexiveObjectPropertyAxiom.Factory,
			ElkObjectPropertyDomainAxiom.Factory,
			ElkObjectPropertyRangeAxiom.Factory,
			ElkReflexiveObjectPropertyAxiom.Factory,
			ElkSubObjectPropertyOfAxiom.Factory,
			ElkSymmetricObjectPropertyAxiom.Factory,
			ElkTransitiveObjectPropertyAxiom.Factory {

		// combined interface

	}

}
