
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDisjointDataPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkDisjointDataPropertiesAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDisjointDataPropertiesAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDisjointDataPropertiesAxiomFilter extends
		ElkDisjointDataPropertiesAxiomVisitor<ElkDisjointDataPropertiesAxiom> {

	// nothing else

}