
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAssertionAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkAssertionAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the output type of the visitor
 */
public interface ElkAssertionAxiomVisitor<O> extends
		ElkClassAssertionAxiomVisitor<O>,
		ElkDifferentIndividualsAxiomVisitor<O>,
		ElkPropertyAssertionAxiomVisitor<O>, ElkSameIndividualAxiomVisitor<O> {

	// combined visitor

}
