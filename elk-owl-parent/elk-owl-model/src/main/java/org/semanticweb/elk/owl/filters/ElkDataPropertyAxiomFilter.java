 
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAxiom;

/**
 * A filter producing objects in {@link ElkDataPropertyAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataPropertyAxiomFilter extends
		ElkDataPropertyDomainAxiomFilter, ElkDataPropertyRangeAxiomFilter,
		ElkDisjointDataPropertiesAxiomFilter,
		ElkEquivalentDataPropertiesAxiomFilter,
		ElkFunctionalDataPropertyAxiomFilter, ElkSubDataPropertyOfAxiomFilter {

	// combined visitor

}
