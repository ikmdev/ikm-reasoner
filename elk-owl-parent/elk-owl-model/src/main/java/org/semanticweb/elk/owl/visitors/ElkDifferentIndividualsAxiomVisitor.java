
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDifferentIndividualsAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDifferentIndividualsAxiomVisitor<O> {

	public O visit(ElkDifferentIndividualsAxiom axiom);

}
