
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRangeAxiom;



/**
 * A filter producing objects in {@link ElkPropertyRangeAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkPropertyRangeAxiomFilter extends
		ElkAnnotationPropertyRangeAxiomFilter, ElkDataPropertyRangeAxiomFilter,
		ElkObjectPropertyRangeAxiomFilter {

	// combined visitor

}
