
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyRangeAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkAnnotationPropertyRangeAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnnotationPropertyRangeAxiomVisitor<O> {

	O visit(ElkAnnotationPropertyRangeAxiom axiom);

}
