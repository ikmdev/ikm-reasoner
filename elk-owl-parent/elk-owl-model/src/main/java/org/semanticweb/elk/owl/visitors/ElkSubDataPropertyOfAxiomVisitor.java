
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkSubDataPropertyOfAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkSubDataPropertyOfAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkSubDataPropertyOfAxiomVisitor<O> {

	public O visit(ElkSubDataPropertyOfAxiom axiom);

}
