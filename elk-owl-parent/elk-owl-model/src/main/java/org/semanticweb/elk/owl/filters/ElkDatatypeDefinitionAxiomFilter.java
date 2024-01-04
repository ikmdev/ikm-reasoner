
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDatatypeDefinitionAxiom;
import org.semanticweb.elk.owl.visitors.ElkDatatypeDefinitionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDatatypeDefinitionAxiom} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDatatypeDefinitionAxiomFilter extends
		ElkDatatypeDefinitionAxiomVisitor<ElkDatatypeDefinitionAxiom> {

	// nothing else

}