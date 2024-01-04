
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkAnnotationAxiom}.
 * 
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the output type of the visitor
 */
public interface ElkAnnotationAxiomVisitor<O> extends
		ElkAnnotationAssertionAxiomVisitor<O>,
		ElkAnnotationPropertyDomainAxiomVisitor<O>,
		ElkAnnotationPropertyRangeAxiomVisitor<O>,
		ElkSubAnnotationPropertyOfAxiomVisitor<O> {

	// combined visitor

}
