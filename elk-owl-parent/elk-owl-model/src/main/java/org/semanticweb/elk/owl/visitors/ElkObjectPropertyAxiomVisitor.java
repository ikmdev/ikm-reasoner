
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkObjectPropertyAxiom}.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectPropertyAxiomVisitor<O> extends
		ElkAsymmetricObjectPropertyAxiomVisitor<O>,
		ElkDisjointObjectPropertiesAxiomVisitor<O>,
		ElkEquivalentObjectPropertiesAxiomVisitor<O>,
		ElkFunctionalObjectPropertyAxiomVisitor<O>,
		ElkInverseFunctionalObjectPropertyAxiomVisitor<O>,
		ElkInverseObjectPropertiesAxiomVisitor<O>,
		ElkIrreflexiveObjectPropertyAxiomVisitor<O>,
		ElkObjectPropertyDomainAxiomVisitor<O>,
		ElkObjectPropertyRangeAxiomVisitor<O>,
		ElkReflexiveObjectPropertyAxiomVisitor<O>,
		ElkSubObjectPropertyOfAxiomVisitor<O>,
		ElkSymmetricObjectPropertyAxiomVisitor<O>,
		ElkTransitiveObjectPropertyAxiomVisitor<O> {

	// combined visitor
}
