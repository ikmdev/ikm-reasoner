
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkAsymmetricObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkAsymmetricObjectPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAsymmetricObjectPropertyAxiomVisitor<O> {

	public O visit(ElkAsymmetricObjectPropertyAxiom axiom);

}
