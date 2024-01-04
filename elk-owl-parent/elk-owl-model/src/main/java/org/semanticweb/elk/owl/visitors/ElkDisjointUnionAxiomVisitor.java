
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkDisjointUnionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDisjointUnionAxiomVisitor<O> {

	public O visit(ElkDisjointUnionAxiom axiom);

}
