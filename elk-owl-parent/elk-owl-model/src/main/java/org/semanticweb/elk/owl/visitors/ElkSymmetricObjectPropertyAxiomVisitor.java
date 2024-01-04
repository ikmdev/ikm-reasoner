
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkSymmetricObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkSymmetricObjectPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkSymmetricObjectPropertyAxiomVisitor<O> {

	public O visit(ElkSymmetricObjectPropertyAxiom axiom);

}
