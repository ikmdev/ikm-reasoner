
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataPropertyDomainAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkDataPropertyDomainAxiom}
 * .
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataPropertyDomainAxiomVisitor<O> {

	public O visit(ElkDataPropertyDomainAxiom axiom);

}
