
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRangeAxiom;



/**
 * Visitor pattern interface for instances of {@link ElkPropertyRangeAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkPropertyRangeAxiomVisitor<O> extends
		ElkAnnotationPropertyRangeAxiomVisitor<O>,
		ElkDataPropertyRangeAxiomVisitor<O>,
		ElkObjectPropertyRangeAxiomVisitor<O> {

	// combined visitor

}
