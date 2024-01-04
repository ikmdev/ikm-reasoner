
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkPropertyAssertionAxiom;



/**
 * A filter producing objects in {@link ElkPropertyAssertionAxiom} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkPropertyAssertionAxiomFilter extends
		ElkDataPropertyAssertionAxiomFilter,
		ElkNegativeDataPropertyAssertionAxiomFilter,
		ElkNegativeObjectPropertyAssertionAxiomFilter,
		ElkObjectPropertyAssertionAxiomFilter {

	// combined visitor

}
