
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkReflexiveObjectPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkReflexiveObjectPropertyAxiomVisitor<O> {

	public O visit(ElkReflexiveObjectPropertyAxiom axiom);

}
