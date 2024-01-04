
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkClassAssertionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkClassAssertionAxiomVisitor<O> {

	public O visit(ElkClassAssertionAxiom axiom);

}
