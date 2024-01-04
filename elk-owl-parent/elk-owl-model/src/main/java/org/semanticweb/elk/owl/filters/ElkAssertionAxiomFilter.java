
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAssertionAxiom;

/**
 * A filter producing objects in {@link ElkAssertionAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAssertionAxiomFilter extends
		ElkClassAssertionAxiomFilter,
		ElkDifferentIndividualsAxiomFilter,
		ElkPropertyAssertionAxiomFilter, ElkSameIndividualAxiomFilter {

	// combined visitor

}
