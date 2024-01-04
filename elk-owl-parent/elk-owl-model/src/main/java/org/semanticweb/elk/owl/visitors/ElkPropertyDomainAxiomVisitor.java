
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkPropertyDomainAxiom;



/**
 * Visitor pattern interface for instances of {@link ElkPropertyDomainAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkPropertyDomainAxiomVisitor<O> extends
		ElkAnnotationPropertyDomainAxiomVisitor<O>,
		ElkDataPropertyDomainAxiomVisitor<O>,
		ElkObjectPropertyDomainAxiomVisitor<O> {

	// combined visitor

}
