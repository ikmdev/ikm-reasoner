
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkDisjointClassesAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDisjointClassesAxiomVisitor<O> {

	public O visit(ElkDisjointClassesAxiom axiom);

}
