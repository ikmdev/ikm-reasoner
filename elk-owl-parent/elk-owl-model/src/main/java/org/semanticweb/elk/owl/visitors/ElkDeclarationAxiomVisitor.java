
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkDeclarationAxiom}.
 * 
 * @author Frantisek Simancik
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDeclarationAxiomVisitor<O> {

	O visit(ElkDeclarationAxiom axiom);
}
