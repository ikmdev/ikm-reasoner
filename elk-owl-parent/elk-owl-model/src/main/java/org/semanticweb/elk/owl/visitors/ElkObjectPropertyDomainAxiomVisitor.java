
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectPropertyDomainAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectPropertyDomainAxiomVisitor<O> {

	public O visit(ElkObjectPropertyDomainAxiom axiom);

}
