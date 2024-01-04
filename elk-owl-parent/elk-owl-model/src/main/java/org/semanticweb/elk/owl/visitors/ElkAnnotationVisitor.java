
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAnnotation;

/**
 * Visitor pattern interface for instances of {@link ElkAnnotation}.
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnnotationVisitor<O> {

	O visit(ElkAnnotation annotation);

}
