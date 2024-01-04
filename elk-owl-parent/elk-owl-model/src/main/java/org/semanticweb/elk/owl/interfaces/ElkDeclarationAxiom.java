
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDeclarationAxiomVisitor;

/**
 * Corresponds to a <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Entity_Declarations_and_Typing"
 * >Declaration Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * 
 */
public interface ElkDeclarationAxiom extends ElkAxiom {

	/**
	 * Get the entity that this declaration axioms refers to.
	 * 
	 * @return the entity that the declaration refers to
	 */
	public ElkEntity getEntity();

	/**
	 * Accept an {@link ElkDeclarationAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDeclarationAxiomVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDeclarationAxiom}.
		 * 
		 * @param entity
		 *            the {@link ElkEntity} for which the axiom should be created
		 * @return an {@link ElkDeclarationAxiom} corresponding to the input
		 */
		public ElkDeclarationAxiom getDeclarationAxiom(ElkEntity entity);

	}

}
