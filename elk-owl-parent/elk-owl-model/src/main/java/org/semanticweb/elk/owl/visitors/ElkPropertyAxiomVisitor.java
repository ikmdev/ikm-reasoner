
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkPropertyAxiom;



/**
 * Visitor pattern interface for instances of {@link ElkPropertyAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkPropertyAxiomVisitor<O> extends
		ElkAsymmetricObjectPropertyAxiomVisitor<O>,
		ElkFunctionalDataPropertyAxiomVisitor<O>,
		ElkFunctionalObjectPropertyAxiomVisitor<O>,
		ElkInverseFunctionalObjectPropertyAxiomVisitor<O>,
		ElkIrreflexiveObjectPropertyAxiomVisitor<O>,
		ElkPropertyAssertionAxiomVisitor<O>, ElkPropertyDomainAxiomVisitor<O>,
		ElkPropertyRangeAxiomVisitor<O>,
		ElkReflexiveObjectPropertyAxiomVisitor<O>,
		ElkSymmetricObjectPropertyAxiomVisitor<O>,
		ElkTransitiveObjectPropertyAxiomVisitor<O> {

	// combined visitor

}
