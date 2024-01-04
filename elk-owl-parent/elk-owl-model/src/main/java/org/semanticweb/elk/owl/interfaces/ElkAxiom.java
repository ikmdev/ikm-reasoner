
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;

/**
 * Corresponds to an <a href= "http://www.w3.org/TR/owl2-syntax/#Axioms">axiom
 * <a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkAxiom extends ElkObject {

	// TODO getAnnotation();

	/**
	 * Accept an {@link ElkAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ElkAnnotationAxiom.Factory, ElkAssertionAxiom.Factory,
			ElkClassAxiom.Factory, ElkDataPropertyAxiom.Factory,
			ElkDatatypeDefinitionAxiom.Factory, ElkDeclarationAxiom.Factory,
			ElkHasKeyAxiom.Factory, ElkObjectPropertyAxiom.Factory,
			ElkPropertyAxiom.Factory, ElkSWRLRule.Factory {

		// combined interface

	}

}
