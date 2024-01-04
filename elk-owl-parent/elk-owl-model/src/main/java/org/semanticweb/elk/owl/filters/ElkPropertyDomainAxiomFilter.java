
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkPropertyDomainAxiom;



/**
 * A filter producing objects in {@link ElkPropertyDomainAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkPropertyDomainAxiomFilter extends
		ElkAnnotationPropertyDomainAxiomFilter,
		ElkDataPropertyDomainAxiomFilter, ElkObjectPropertyDomainAxiomFilter {

	// combined visitor

}
