
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Assertions">Assertions<a> in the
 * OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkAssertionAxiom extends ElkAxiom {

	/**
	 * Accept an {@link ElkAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkClassAssertionAxiom.Factory,
			ElkDifferentIndividualsAxiom.Factory,
			ElkPropertyAssertionAxiom.Factory, ElkSameIndividualAxiom.Factory {

		// combined interface

	}

}
