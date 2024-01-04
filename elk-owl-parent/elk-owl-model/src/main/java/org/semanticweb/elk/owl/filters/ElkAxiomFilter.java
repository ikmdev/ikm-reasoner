
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * A filter producing objects in {@link ElkAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAxiomFilter extends ElkAnnotationAxiomFilter,
		ElkAssertionAxiomFilter, ElkClassAxiomFilter,
		ElkDataPropertyAxiomFilter, ElkDatatypeDefinitionAxiomFilter,
		ElkDeclarationAxiomFilter, ElkHasKeyAxiomFilter,
		ElkObjectPropertyAxiomFilter, ElkPropertyAxiomFilter,
		ElkSWRLRuleFilter {

	// combined visitor
}
