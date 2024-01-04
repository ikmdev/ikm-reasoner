
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkNegativeDataPropertyAssertionAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkNegativeDataPropertyAssertionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkNegativeDataPropertyAssertionAxiomVisitor<O> {

	public O visit(ElkNegativeDataPropertyAssertionAxiom axiom);

}
