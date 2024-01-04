
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAxiom;

/**
 * A filter producing objects in {@link ElkObjectPropertyAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectPropertyAxiomFilter extends
		ElkAsymmetricObjectPropertyAxiomFilter,
		ElkDisjointObjectPropertiesAxiomFilter,
		ElkEquivalentObjectPropertiesAxiomFilter,
		ElkFunctionalObjectPropertyAxiomFilter,
		ElkInverseFunctionalObjectPropertyAxiomFilter,
		ElkInverseObjectPropertiesAxiomFilter,
		ElkIrreflexiveObjectPropertyAxiomFilter,
		ElkObjectPropertyDomainAxiomFilter, ElkObjectPropertyRangeAxiomFilter,
		ElkReflexiveObjectPropertyAxiomFilter,
		ElkSubObjectPropertyOfAxiomFilter,
		ElkSymmetricObjectPropertyAxiomFilter,
		ElkTransitiveObjectPropertyAxiomFilter {

	// combined visitor
}
