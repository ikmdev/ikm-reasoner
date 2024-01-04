
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkIrreflexiveObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkIrreflexiveObjectPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkIrreflexiveObjectPropertyAxiomVisitor<O> {

	public O visit(ElkIrreflexiveObjectPropertyAxiom axiom);

}
