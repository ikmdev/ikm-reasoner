
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyDomainAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkAnnotationPropertyDomainAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnnotationPropertyDomainAxiomVisitor<O> {

	O visit(ElkAnnotationPropertyDomainAxiom axiom);

}
