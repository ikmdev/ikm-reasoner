
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;

/**
 * /** A generic interface for object and data property assertion axioms.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this assertion
 * @param <S>
 *            the type of the subject of this assertion
 * @param <O>
 *            the type of the object of this assertion
 */
public interface ElkPropertyAssertionAxiom<P, S, O>
		extends ElkPropertyAxiom<P>, ElkAssertionAxiom {

	/**
	 * Get the subject of this restriction.
	 * 
	 * @return the subject of this restriction
	 */
	S getSubject();

	/**
	 * Get the object of this restriction.
	 * 
	 * @return the object of this restriction
	 */
	O getObject();

	/**
	 * Accept an {@link ElkPropertyAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <T> T accept(ElkPropertyAssertionAxiomVisitor<T> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataPropertyAssertionAxiom.Factory,
			ElkNegativeDataPropertyAssertionAxiom.Factory,
			ElkNegativeObjectPropertyAssertionAxiom.Factory,
			ElkObjectPropertyAssertionAxiom.Factory {

		// combined interface

	}

}
