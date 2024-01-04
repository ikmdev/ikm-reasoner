
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationSubject;

/**
 * Visitor pattern interface for instances of {@link ElkAnnotationSubject}.
 * 
 * @author Frantisek Simancik
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnnotationSubjectVisitor<O> extends ElkIriVisitor<O>,
		ElkAnonymousIndividualVisitor<O> {

	// combined visitor

}
