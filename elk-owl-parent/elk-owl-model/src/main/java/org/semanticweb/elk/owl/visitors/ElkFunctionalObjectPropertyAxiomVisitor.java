
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkFunctionalObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkFunctionalObjectPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkFunctionalObjectPropertyAxiomVisitor<O> {

	public O visit(ElkFunctionalObjectPropertyAxiom axiom);

}
