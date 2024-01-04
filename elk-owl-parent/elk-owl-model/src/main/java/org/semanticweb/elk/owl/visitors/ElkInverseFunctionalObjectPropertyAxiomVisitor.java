
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkInverseFunctionalObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkInverseFunctionalObjectPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkInverseFunctionalObjectPropertyAxiomVisitor<O> {

	public O visit(ElkInverseFunctionalObjectPropertyAxiom axiom);

}
