 
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkSubAnnotationPropertyOfAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkSubAnnotationPropertyOfAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkSubAnnotationPropertyOfAxiomVisitor<O> {

	O visit(ElkSubAnnotationPropertyOfAxiom axiom);

}
