
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAssertionAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDataPropertyAssertionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataPropertyAssertionAxiomVisitor<O> {

	public O visit(ElkDataPropertyAssertionAxiom axiom);

}
