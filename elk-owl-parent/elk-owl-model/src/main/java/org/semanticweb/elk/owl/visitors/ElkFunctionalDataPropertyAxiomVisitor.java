
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkFunctionalDataPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkFunctionalDataPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkFunctionalDataPropertyAxiomVisitor<O> {

	public O visit(ElkFunctionalDataPropertyAxiom axiom);

}
