
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkAnnotationAssertionAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkAnnotationAssertionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnnotationAssertionAxiomVisitor<O> {

	public O visit(ElkAnnotationAssertionAxiom axiom);

}
