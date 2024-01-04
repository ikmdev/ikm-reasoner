
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectPropertyAssertionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectPropertyAssertionAxiomVisitor<O> {

	public O visit(ElkObjectPropertyAssertionAxiom axiom);

}
