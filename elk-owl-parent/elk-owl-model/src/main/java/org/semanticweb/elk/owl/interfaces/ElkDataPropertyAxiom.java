
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Data_Property_Axioms">Data
 * Property Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataPropertyAxiom extends ElkAxiom {

	/**
	 * Accept an {@link ElkDataPropertyAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataPropertyDomainAxiom.Factory,
			ElkDataPropertyRangeAxiom.Factory,
			ElkDisjointDataPropertiesAxiom.Factory,
			ElkEquivalentDataPropertiesAxiom.Factory,
			ElkFunctionalDataPropertyAxiom.Factory,
			ElkSubDataPropertyOfAxiom.Factory {

		// combined interface

	}

}
