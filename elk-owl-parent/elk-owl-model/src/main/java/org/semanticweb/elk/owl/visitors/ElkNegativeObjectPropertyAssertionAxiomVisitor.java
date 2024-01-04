 
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkNegativeObjectPropertyAssertionAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkNegativeObjectPropertyAssertionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkNegativeObjectPropertyAssertionAxiomVisitor<O> {

	public O visit(ElkNegativeObjectPropertyAssertionAxiom axiom);

}
