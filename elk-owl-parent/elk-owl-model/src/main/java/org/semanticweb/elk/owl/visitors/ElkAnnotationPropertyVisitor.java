
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;

/**
 * Visitor pattern interface for instances of {@link ElkAnnotationProperty}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnnotationPropertyVisitor<O> {

	public O visit(ElkAnnotationProperty expression);

}
