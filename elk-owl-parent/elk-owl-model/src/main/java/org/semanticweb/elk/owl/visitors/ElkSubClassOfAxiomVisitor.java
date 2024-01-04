
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkSubClassOfAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkSubClassOfAxiomVisitor<O> {

	public O visit(ElkSubClassOfAxiom axiom);

}
