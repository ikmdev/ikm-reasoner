 
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDisjointObjectPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkDisjointObjectPropertiesAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDisjointObjectPropertiesAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDisjointObjectPropertiesAxiomFilter
		extends
		ElkDisjointObjectPropertiesAxiomVisitor<ElkDisjointObjectPropertiesAxiom> {

	// nothing else

}