
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationValue;

/**
 * Visitor pattern interface for instances of {@link ElkAnnotationValue}.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnnotationValueVisitor<O> extends ElkIriVisitor<O>,
		ElkLiteralVisitor<O>, ElkAnonymousIndividualVisitor<O> {

	// combined visitor

}
