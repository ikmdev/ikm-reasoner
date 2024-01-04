
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkSubObjectPropertyOfAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkSubObjectPropertyOfAxiomVisitor<O> {

	public O visit(ElkSubObjectPropertyOfAxiom axiom);

}
