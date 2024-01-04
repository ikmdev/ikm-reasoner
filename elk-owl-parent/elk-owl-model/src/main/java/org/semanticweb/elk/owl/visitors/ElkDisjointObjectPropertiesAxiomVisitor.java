
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDisjointObjectPropertiesAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDisjointObjectPropertiesAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDisjointObjectPropertiesAxiomVisitor<O> {

	public O visit(ElkDisjointObjectPropertiesAxiom axiom);

}
