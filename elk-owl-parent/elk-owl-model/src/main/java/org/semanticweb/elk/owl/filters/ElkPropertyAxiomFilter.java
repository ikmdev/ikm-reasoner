
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkPropertyAxiom;



/**
 * A filter producing objects in {@link ElkPropertyAxiom} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkPropertyAxiomFilter extends
		ElkAsymmetricObjectPropertyAxiomFilter,
		ElkFunctionalDataPropertyAxiomFilter,
		ElkFunctionalObjectPropertyAxiomFilter,
		ElkInverseFunctionalObjectPropertyAxiomFilter,
		ElkIrreflexiveObjectPropertyAxiomFilter,
		ElkPropertyAssertionAxiomFilter, ElkPropertyDomainAxiomFilter,
		ElkPropertyRangeAxiomFilter, ElkReflexiveObjectPropertyAxiomFilter,
		ElkSymmetricObjectPropertyAxiomFilter,
		ElkTransitiveObjectPropertyAxiomFilter {

	// combined visitor

}
