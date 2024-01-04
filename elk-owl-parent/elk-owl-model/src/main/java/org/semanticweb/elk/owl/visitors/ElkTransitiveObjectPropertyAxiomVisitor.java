
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkTransitiveObjectPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkTransitiveObjectPropertyAxiomVisitor<O> {

	public O visit(ElkTransitiveObjectPropertyAxiom axiom);

}
