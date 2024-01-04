
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDisjointDataPropertiesAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDisjointDataPropertiesAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDisjointDataPropertiesAxiomVisitor<O> {

	public O visit(ElkDisjointDataPropertiesAxiom axiom);

}
